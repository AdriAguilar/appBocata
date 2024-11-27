package com.example.mibocata

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import java.util.Locale

class MenuAdapter(context: Context, menu: Menu): ArrayAdapter<String>(context, 0) {
    private val menu: Menu = menu
    private val diasSemana = listOf("Lunes", "Martes", "Miercoles", "Jueves", "Viernes")

    override fun getCount(): Int {
        return diasSemana.size
    }

    override fun getItem(position: Int): String {
        return diasSemana[position]
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_bocadillo, parent, false)
        val dia = diasSemana[position]

        val bocadilloDia = when(dia) {
            "Lunes" -> menu.lunes
            "Martes" -> menu.martes
            "Miercoles" -> menu.miercoles
            "Jueves" -> menu.jueves
            "Viernes" -> menu.viernes
            else -> throw IllegalArgumentException("Día no válido")
        }

        val diaTextView = view.findViewById<TextView>(R.id.bocadillo_dia)
        val bocadilloCalienteTextView = view.findViewById<TextView>(R.id.bocadillo_caliente)
        val ingredientesCalienteTextView = view.findViewById<TextView>(R.id.bocadillo_caliente_ingre)
        val bocadilloFrioTextView = view.findViewById<TextView>(R.id.bocadillo_frio)
        val ingredientesFrioTextView = view.findViewById<TextView>(R.id.bocadillo_frio_ingre)

        if (dia == "Miercoles") {
            diaTextView.text = "Miércoles"
        } else {
            diaTextView.text = dia
        }

        bocadilloCalienteTextView.text = "${bocadilloDia.caliente.nombre} (Caliente)"

        val ingredientesCaliente = bocadilloDia.caliente.ingredientes.joinToString(", ")
        ingredientesCalienteTextView.text = "Ingredientes: $ingredientesCaliente"

        bocadilloFrioTextView.text = "${bocadilloDia.frio.nombre} (Frío)"

        val ingredientesFrio = bocadilloDia.frio.ingredientes.joinToString(", ")
        ingredientesFrioTextView.text = "Ingredientes: $ingredientesFrio"

        return view
    }
}