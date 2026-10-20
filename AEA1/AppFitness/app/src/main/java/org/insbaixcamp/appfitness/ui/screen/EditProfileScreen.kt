package org.insbaixcamp.appfitness.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import org.insbaixcamp.appfitness.R

@Composable
fun EditProfileScreen(
    nom: String,
    email: String,
    dataNaixement: String,
    genere: String,
    onGuardar: () -> Unit,
    pes: Double,
    alcada: Int,
    objectiu: String,
    pesObjectiu: Double
) {
    var nomNou by remember { mutableStateOf(nom) }
    var emailNou by remember { mutableStateOf(email) }
    var dataNaixementNova by remember { mutableStateOf(dataNaixement) }
    var genereNou by remember { mutableStateOf(genere) }
    var alcadaNova by remember { mutableStateOf(alcada) }
    var pesNou by remember { mutableStateOf(pes) }
    var pesObjectiuNou by remember { mutableStateOf(pesObjectiu) }



    Column(Modifier.fillMaxWidth()) {

        Row(Modifier.fillMaxWidth().padding(all = 16.dp)){
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(Color.Black)
            )
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.End) {
                Button(onClick = {}) {
                    Text(
                        text = "Canviar foto"
                    )
                }
                Button(onClick = {}) {
                    Text(
                        text = "Canviar foto"
                    )
                }
            }

        }
        Text(
            text = "Dades personals",
            modifier = Modifier.padding(all = 8.dp),

        )
        OutlinedTextField(
            value = nomNou,
            onValueChange = { nomNou = it },
            label = { Text("Nom complet") },
            modifier = Modifier.padding(all = 8.dp).fillMaxWidth()
        )

        OutlinedTextField(
            value = emailNou,
            onValueChange = { emailNou = it },
            label = { Text("Correu electrònic") },
            modifier = Modifier.padding(all = 8.dp).fillMaxWidth()
        )

        OutlinedTextField(
            value = dataNaixementNova,
            onValueChange = { dataNaixementNova = it },
            label = { Text("Data de naixement") } ,
            modifier = Modifier.padding(all = 8.dp).fillMaxWidth()
        )

        OutlinedTextField(
            value = genereNou,
            onValueChange = { genereNou = it },
            label = { Text("Gènere") } ,
            modifier = Modifier.padding(all = 8.dp).fillMaxWidth()
        )

        Text(
            text = "Dades físiques",
            modifier = Modifier.padding(all = 8.dp)
        )
        Row(){
            OutlinedTextField(
                value = alcadaNova.toString(),
                onValueChange = { alcadaNova.toString() },
                label = { Text("Alçada") } ,
                modifier = Modifier.padding(all = 8.dp)
            )

            OutlinedTextField(
                value = pesNou.toString(),
                onValueChange = { pesNou.toString() },
                label = { Text("Pes") } ,
                modifier = Modifier.padding(all = 8.dp)
            )

            OutlinedTextField(
                value = pesObjectiuNou.toString(),
                onValueChange = { pesObjectiuNou.toString() },
                label = { Text("Pes objectiu") } ,
                modifier = Modifier.padding(all = 8.dp)
            )


        }


    }



}