package org.insbaixcamp.appfitness.ui.screen

import androidx.compose.foundation.Image
import org.insbaixcamp.appfitness.R
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Calendar

@Composable
fun ProfileScreen(
    nom: String,
    email: String,
    dataNaixement: String,
    genere: String,
    onEditar: () -> Unit,
    pes: Double,
    alcada: Int,
    objectiu: String,
    pesObjectiu: Double
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Caixa de foto i nom
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null,
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(Color.Black)
            )
            Text(
                text = nom,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Text(
                text = email,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Targeta amb informació d'edat, gènere, alçada i pes
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                // Caixa de data de naixement
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Edat")
                    val anys: Int = Calendar.getInstance().get(Calendar.YEAR) - dataNaixement.split("/")[2].toInt()
                    Text(
                        text = anys.toString(),
                        fontWeight = FontWeight.Bold)
                }
                // Caixa gènere
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Gènere")
                    Text(
                        text = genere,
                        fontWeight = FontWeight.Bold
                    )
                }
                // Caixa alçada
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Alçada")
                    Text(
                        text = "$alcada cm",
                        fontWeight = FontWeight.Bold
                    )
                }
                // Caixa pes
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Pes")
                    Text(
                        text = "$pes Kg",
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Títol d'Objectiu centralitzat
        Text(
            text = "Objectiu",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Left,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Card d'Objectiu ampliada i centrada
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = objectiu,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp)
            )
        }
    }
    Column(
        Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.End
    ) {
        Image(
            painter = painterResource(id = android.R.drawable.ic_menu_edit),
            contentDescription = null,
            modifier = Modifier
                .size(45.dp)
                .clickable(onClick = onEditar)



        )
    }




}