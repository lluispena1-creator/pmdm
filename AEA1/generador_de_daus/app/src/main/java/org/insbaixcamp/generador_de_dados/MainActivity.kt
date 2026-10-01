package org.insbaixcamp.generador_de_dados

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btD6 = findViewById<Button>(R.id.bt_d6)
        val btD8 = findViewById<Button>(R.id.bt_d8)
        val btD10 = findViewById<Button>(R.id.bt_d10)
        val btD20 = findViewById<Button>(R.id.bt_d20)
        val btTirar = findViewById<Button>(R.id.bt_tirar)
        val tvTirada = findViewById<TextView>(R.id.tv_tirada)

        var numDau = 0

        btD6.setOnClickListener {
            numDau = 6
        }
        btD8.setOnClickListener {
            numDau = 8
        }
        btD10.setOnClickListener {
            numDau = 10
        }
        btD20.setOnClickListener {
            numDau = 20
        }

        btTirar.setOnClickListener {
            if (numDau > 0) {
                val resultado = (1..numDau).random()
                tvTirada.text = resultado.toString()
            } else {
                tvTirada.text = "Primer sel·lecciona un dau"
            }
        }
    }
}