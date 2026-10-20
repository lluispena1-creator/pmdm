package org.insbaixcamp.appfitness

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import org.insbaixcamp.appfitness.ui.screen.EditProfileScreen
import org.insbaixcamp.appfitness.ui.screen.ProfileScreen
import org.insbaixcamp.appfitness.ui.theme.AppFitnessTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppFitnessTheme {
                    FitnessApp()
            }
        }
    }
}
@Composable
fun FitnessApp() {
    var nom: String by remember { mutableStateOf("Lluís Pena") }
    var email: String by remember { mutableStateOf("lluis.pena@exemple.com") }
    var dataNaixement: String by remember { mutableStateOf(value = "29/09/2006") }
    var genere: String by remember { mutableStateOf("Masculí") }
    var editant: Boolean by remember { mutableStateOf(true) }
    var pes: Double by remember { mutableStateOf(70.0) }
    var alcada: Int by remember { mutableStateOf(175) }
    var objectiu: String by remember { mutableStateOf("Guanyar massa muscular") }
    var pesObjectiu: Double by remember { mutableStateOf(75.0) }


    if(editant){
        EditProfileScreen(nom, email, dataNaixement, genere, onGuardar = {editant = false}, pes, alcada, objectiu, pesObjectiu)
    }else{
        ProfileScreen(nom, email, dataNaixement, genere, onEditar = {editant = true}, pes, alcada, objectiu, pesObjectiu)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppFitnessTheme {
        FitnessApp()
    }
}