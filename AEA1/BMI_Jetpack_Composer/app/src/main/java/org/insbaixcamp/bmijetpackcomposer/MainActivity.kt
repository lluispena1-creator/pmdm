package org.insbaixcamp.bmijetpackcomposer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.insbaixcamp.bmijetpackcomposer.ui.theme.BMIJetpackComposerTheme
import androidx.compose.runtime.mutableStateOf
import android.R.attr.label
import android.R.attr.value
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.material3.Slider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BMIJetpackComposerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    BMIScreen()
                }
            }
        }
    }
}

@Preview
@Composable
fun BMIScreen(){
    var name by remember { mutableStateOf(value = "") }
    var pes by remember { mutableStateOf(value = 80) }
    var alcada by remember { mutableStateOf(value = 180) }
    var bmi:Float by remember { mutableStateOf(value = 0f) }
    Column() {
        Text(text = "BMI Calculator")
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") }
        )
    Text(text = "Pes")
        Row() {
            Text(text = "$pes Kg")
            Button(onClick = {
                pes++
            }) {
                Text(text = "+")
            }
            Button(onClick = {
                pes--
            }) {
                Text(text = "-")
            }
            }
        Text(text = "Alçada: $alcada cm")
        Slider(
            value = alcada.toFloat(),
            onValueChange = { alcada = it.toInt() },
            valueRange = 100f..200f
        )
        Button(
            onClick = {
                var alcadaMetres = alcada.toFloat() / 100
                bmi = (pes / (alcadaMetres * alcadaMetres))
            }
        ) {
            Text(text = "Calcular BMI")
        }
        if (bmi != 0f) {
            Text(text = "El teu BMI és $bmi")
        }
    }

    }




@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}