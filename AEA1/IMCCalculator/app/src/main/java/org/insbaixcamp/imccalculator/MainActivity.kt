package org.insbaixcamp.imccalculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private var nom: String = ""
    private var pes: Int = 80
    private var alcada: Int = 170

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btPlus: Button = findViewById(R.id.bt_plus)
        val btMinus: Button = findViewById(R.id.bt_minus)
        val tvPes: TextView = findViewById(R.id.tv_pes)
        val tvNameResult: TextView = findViewById(R.id.tv_nameResult)
        val btCalcular: Button = findViewById(R.id.bt_calcular)
        val tvImcResult: TextView = findViewById(R.id.tv_imcResult)
        val etNom: EditText = findViewById(R.id.et_nom)

        val sbAlcada: SeekBar = findViewById(R.id.sb_alcada)
        val tvAlcada: TextView = findViewById(R.id.tv_alcada)

        sbAlcada.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                alcada = progress
                tvAlcada.text = "$alcada cm"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        btPlus.setOnClickListener {
            pes++
            tvPes.text = pes.toString()
        }

        btMinus.setOnClickListener {
            pes--
            tvPes.text = pes.toString()
        }

        btCalcular.setOnClickListener {
            nom = etNom.text.toString()
            tvNameResult.text = "Bon dia, $nom"

            // Convertimos la altura a metros en formato decimal (Double)
            val alcadaMetres = alcada / 100.0
            val imc = pes / (alcadaMetres * alcadaMetres)

            tvImcResult.text = String.format(Locale.getDefault(), format="%.2f", imc)
        }
    }
}