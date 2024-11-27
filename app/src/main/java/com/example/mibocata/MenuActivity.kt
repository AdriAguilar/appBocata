package com.example.mibocata

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import java.io.InputStreamReader

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)

        // Navegación
        val historyBtn = findViewById<ImageButton>(R.id.history)
        val profileBtn = findViewById<ImageButton>(R.id.profile)
        val bocataBtn = findViewById<ImageButton>(R.id.bocata)

        historyBtn.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }

        profileBtn.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        bocataBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Menu
        val inputStream = resources.openRawResource(R.raw.menu)
        val reader = InputStreamReader(inputStream)

        val gson = Gson()
        val menu = gson.fromJson(reader, Menu::class.java)

        val menuAdapter = MenuAdapter(this, menu)
        val listView = findViewById<ListView>(R.id.menu_list)
        listView.adapter = menuAdapter
    }
}