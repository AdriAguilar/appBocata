package com.example.mibocata

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)

        val sharedPref = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        val name = sharedPref.getString("name", "Value")

        val editTextName = findViewById<EditText>(R.id.nombre)
        editTextName.setText(name)

        // Navegación
        val logoutBtn = findViewById<Button>(R.id.logoutBtn)
        val historyBtn = findViewById<ImageButton>(R.id.history)
        val bocataBtn = findViewById<ImageButton>(R.id.bocata)
        val menuBtn = findViewById<ImageButton>(R.id.menu)


        logoutBtn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        historyBtn.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }

        bocataBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        menuBtn.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
    }
}