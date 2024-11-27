package com.example.mibocata

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.gson.Gson
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Navegación
        val historyBtn = findViewById<ImageButton>(R.id.history)
        val profileBtn = findViewById<ImageButton>(R.id.profile)
        val menuBtn = findViewById<ImageButton>(R.id.menu)

        historyBtn.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }

        profileBtn.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        menuBtn.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }

        // Bocadillos hoy
        val inputStream = resources.openRawResource(R.raw.menu)
        val reader = InputStreamReader(inputStream)

        val gson = Gson()
        val menu = gson.fromJson(reader, Menu::class.java)

        val bocadilloAdapter = BocadilloAdapter(this, menu)
        val listView = findViewById<ListView>(R.id.seleccion)
        listView.adapter = bocadilloAdapter
    }
}