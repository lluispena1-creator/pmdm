package org.insbaixcamp.bmicalculadora

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.insbaixcamp.bmicalculadora.ui.theme.BMICalculadoraTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BMICalculadoraTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    BMICalculadora()
                }
            }
        }
    }
}

@Composable
fun BMICalculadora(){
    Text(text = "BMICalculadora")
}
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BMICalculadoraTheme {
        BMICalculadora()
    }
}