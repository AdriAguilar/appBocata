package com.example.mibocata

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import java.text.Normalizer
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

class BocadilloAdapter(context: Context, menu: Menu): BaseAdapter() {
    private val context: Context = context
    private val dia: String = obtenerDia()

    private val bocadilloDia: DayMenu = when (dia) {
        "Lunes" -> menu.lunes
        "Martes" -> menu.martes
        "Miercoles" -> menu.miercoles
        "Jueves" -> menu.jueves
        "Viernes" -> menu.viernes
        else -> throw IllegalArgumentException("Día no válido")
    }

    override fun getCount(): Int {
        return 1
    }

    override fun getItem(position: Int): Any {
        return bocadilloDia
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_seleccion_bocata, parent, false)

        var diaToShow = dia
        if (dia == "Miercoles") {
            diaToShow = "Miércoles"
        }

        // Bocata caliente
        val bocadilloCaliente = bocadilloDia.caliente

        val bocadilloCalNombre = view.findViewById<TextView>(R.id.bocadillo_c_nombre)
        bocadilloCalNombre.text = "${bocadilloCaliente.nombre} ($diaToShow)"

        val bocadilloCalIngredientes = view.findViewById<TextView>(R.id.bocadillo_c_ingredientes)
        val ingredientesCaliente = bocadilloCaliente.ingredientes.joinToString(", ")
        bocadilloCalIngredientes.text = "Ingredientes: $ingredientesCaliente"

        // Bocata frio
        val bocadilloFrio = bocadilloDia.frio

        val bocadilloFrioNombre = view.findViewById<TextView>(R.id.bocadillo_f_nombre)
        bocadilloFrioNombre.text = "${bocadilloFrio.nombre} ($diaToShow)"

        val bocadilloFrioIngredientes = view.findViewById<TextView>(R.id.bocadillo_f_ingredientes)
        val ingredientesFrio = bocadilloFrio.ingredientes.joinToString(", ")
        bocadilloFrioIngredientes.text = "Ingredientes: $ingredientesFrio"

        return view
    }
}

fun obtenerDia(): String {
    val diaSemana = LocalDate.now().format(DateTimeFormatter.ofPattern("EEEE", Locale("es", "ES")))
    val dia = Normalizer.normalize(diaSemana, Normalizer.Form.NFD).replace(Regex("\\p{InCombiningDiacriticalMarks}+"), "")
    return dia.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
}