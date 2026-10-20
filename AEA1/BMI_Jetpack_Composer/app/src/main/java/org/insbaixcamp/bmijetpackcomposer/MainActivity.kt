package org.insbaixcamp.bmijetpackcomposer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Height
import androidx.compose.material.icons.filled.MonitorWeight
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.insbaixcamp.bmijetpackcomposer.ui.theme.BMIJetpackComposerTheme
import org.insbaixcamp.bmijetpackcomposer.ui.theme.DbBorderBlack
import org.insbaixcamp.bmijetpackcomposer.ui.theme.DbMajinColor
import org.insbaixcamp.bmijetpackcomposer.ui.theme.DbOrangePrimary
import org.insbaixcamp.bmijetpackcomposer.ui.theme.DbSaiyanColor
import org.insbaixcamp.bmijetpackcomposer.ui.theme.DbShenronColor
import org.insbaixcamp.bmijetpackcomposer.ui.theme.DbYamchaColor
import org.insbaixcamp.bmijetpackcomposer.ui.theme.DbYellowGold
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BMIJetpackComposerTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = { IMCTopBar() }
                ) { innerPadding ->
                    IMCScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IMCTopBar() {
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .clip(CircleShape)
                        .background(DbYellowGold)
                        .border(2.dp, DbBorderBlack, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "🐉",
                        fontSize = 20.sp
                    )
                }
                Text(
                    text = "CALCULADORA DE KI",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = DbOrangePrimary
        ),
        modifier = Modifier.border(BorderStroke(3.dp, DbBorderBlack))
    )
}

enum class BMICategory(
    val levelName: String,
    val iconEmoji: String,
    val color: Color,
    val advice: String
) {
    UNDERWEIGHT(
        levelName = "⚡ NIVELL YAMCHA (< 18.5)",
        iconEmoji = "🥋",
        color = DbYamchaColor,
        advice = "El teu Ki és molt baix! Has de menjar-te unes quantes Mongetes Màgiques (Senzu) i entrenar fort com en Goku!"
    ),
    NORMAL(
        levelName = "🔥 SUPER SAIYAN (18.5 - 24.9)",
        iconEmoji = "🐉",
        color = DbSaiyanColor,
        advice = "Poder de Ki equilibrat! Tens la forma física perfecta de Super Saiyan per protegir la Terra!"
    ),
    OVERWEIGHT(
        levelName = "💥 MODE MAJIN (25.0 - 29.9)",
        iconEmoji = "⚡",
        color = DbMajinColor,
        advice = "Massa energia acumulada! És hora d'entrenar a la Cambra del Temps amb gravetat x100!"
    ),
    OBESE(
        levelName = "🔮 NIVELL SHENRON (≥ 30.0)",
        iconEmoji = "🔮",
        color = DbShenronColor,
        advice = "Poder colossal desbordant! Fes un entrenament intensiu amb el Fullet Tortuga (Kame Sen'nin) per dominar el teu Ki!"
    )
}

fun getBMICategory(bmi: Float): BMICategory {
    return when {
        bmi < 18.5f -> BMICategory.UNDERWEIGHT
        bmi in 18.5f..24.9f -> BMICategory.NORMAL
        bmi in 25.0f..29.9f -> BMICategory.OVERWEIGHT
        else -> BMICategory.OBESE
    }
}

@Composable
fun IMCScreen(modifier: Modifier = Modifier) {
    var name by remember { mutableStateOf("") }
    var pesText by remember { mutableStateOf("75") }
    var alcadaText by remember { mutableStateOf("175") }
    var bmiResult by remember { mutableStateOf<Float?>(null) }

    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(18.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Comic Header Banner
        Card(
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(3.dp, DbBorderBlack),
            colors = CardDefaults.cardColors(
                containerColor = DbYellowGold
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "💥",
                    fontSize = 28.sp
                )
                Text(
                    text = "Introdueix les teves dades per analitzar el teu nivell de combat i IMC!",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = DbBorderBlack
                )
            }
        }

        // Form Fields Component
        IMCInputFields(
            name = name,
            onNameChange = { name = it },
            pesText = pesText,
            onPesChange = { pesText = it },
            alcadaText = alcadaText,
            onAlcadaChange = { alcadaText = it }
        )

        // Dragon Ball Action Button
        Button(
            onClick = {
                val pes = pesText.toFloatOrNull()
                val alcada = alcadaText.toFloatOrNull()
                if (pes != null && alcada != null && alcada > 0f) {
                    val alcadaMetres = alcada / 100f
                    bmiResult = pes / (alcadaMetres * alcadaMetres)
                }
            },
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = DbOrangePrimary,
                contentColor = Color.White
            ),
            border = BorderStroke(3.dp, DbBorderBlack),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 6.dp,
                pressedElevation = 2.dp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Bolt,
                    contentDescription = null,
                    tint = DbYellowGold,
                    modifier = Modifier.size(28.dp)
                )
                Text(
                    text = "MESURA EL TEU KI I IMC! ⚡",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 18.sp
                )
            }
        }

        // Comic Result Card Component
        bmiResult?.let { bmi ->
            AnimatedVisibility(
                visible = true,
                enter = fadeIn() + slideInVertically { it / 2 }
            ) {
                ResultCard(
                    bmi = bmi,
                    name = name
                )
            }
        }
    }
}

@Composable
fun IMCInputFields(
    name: String,
    onNameChange: (String) -> Unit,
    pesText: String,
    onPesChange: (String) -> Unit,
    alcadaText: String,
    onAlcadaChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(3.dp, DbBorderBlack),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Nom Guerrero
            OutlinedTextField(
                value = name,
                onValueChange = onNameChange,
                label = {
                    Text(
                        "Nom del Guerrer/a (opcional)",
                        fontWeight = FontWeight.Bold
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        tint = DbOrangePrimary
                    )
                },
                singleLine = true,
                shape = RoundedCornerShape(10.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = DbOrangePrimary,
                    unfocusedBorderColor = DbBorderBlack,
                    focusedLabelColor = DbOrangePrimary
                ),
                modifier = Modifier.fillMaxWidth()
            )

            // Pes Field
            OutlinedTextField(
                value = pesText,
                onValueChange = onPesChange,
                label = {
                    Text(
                        "Pes (kg)",
                        fontWeight = FontWeight.Bold
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.MonitorWeight,
                        contentDescription = null,
                        tint = DbOrangePrimary
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                shape = RoundedCornerShape(10.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = DbOrangePrimary,
                    unfocusedBorderColor = DbBorderBlack,
                    focusedLabelColor = DbOrangePrimary
                ),
                modifier = Modifier.fillMaxWidth()
            )

            // Alçada Field
            OutlinedTextField(
                value = alcadaText,
                onValueChange = onAlcadaChange,
                label = {
                    Text(
                        "Alçada (cm)",
                        fontWeight = FontWeight.Bold
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Height,
                        contentDescription = null,
                        tint = DbOrangePrimary
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                shape = RoundedCornerShape(10.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = DbOrangePrimary,
                    unfocusedBorderColor = DbBorderBlack,
                    focusedLabelColor = DbOrangePrimary
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun ResultCard(
    bmi: Float,
    name: String,
    modifier: Modifier = Modifier
) {
    val category = getBMICategory(bmi)
    val formattedBmi = String.format(Locale.ROOT, "%.1f", bmi)
    val progress = ((bmi - 15f) / (40f - 15f)).coerceIn(0f, 1f)

    Card(
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(3.dp, DbBorderBlack),
        colors = CardDefaults.cardColors(
            containerColor = category.color.copy(alpha = 0.15f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(22.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Comic Speech Title
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(DbYellowGold)
                    .border(2.dp, DbBorderBlack, RoundedCornerShape(8.dp))
                    .padding(horizontal = 14.dp, vertical = 6.dp)
            ) {
                val greeting = if (name.isNotBlank()) "💥 ANÀLISI DE KI PER A $name 💥" else "💥 ANÀLISI DEL TEU PODER 💥"
                Text(
                    text = greeting,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.ExtraBold,
                    color = DbBorderBlack,
                    textAlign = TextAlign.Center
                )
            }

            // Big BMI Score
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = category.iconEmoji,
                    fontSize = 40.sp
                )
                Text(
                    text = formattedBmi,
                    style = MaterialTheme.typography.headlineLarge.copy(fontSize = 56.sp),
                    fontWeight = FontWeight.ExtraBold,
                    color = category.color
                )
            }

            // Dragon Ball Level Badge
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(category.color)
                    .border(2.dp, DbBorderBlack, RoundedCornerShape(50))
                    .padding(horizontal = 22.dp, vertical = 10.dp)
            ) {
                Text(
                    text = category.levelName,
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.ExtraBold
                )
            }

            // Ki Progress Meter
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = "⚡ NIVELL D'ENERGIA DE KI",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.ExtraBold,
                    color = DbBorderBlack
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(16.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .border(2.dp, DbBorderBlack, RoundedCornerShape(8.dp))
                ) {
                    LinearProgressIndicator(
                        progress = { progress },
                        modifier = Modifier.fillMaxSize(),
                        color = category.color,
                        trackColor = category.color.copy(alpha = 0.25f)
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "15.0 (Min)",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = DbBorderBlack
                    )
                    Text(
                        text = "25.0 (Ideal)",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = DbBorderBlack
                    )
                    Text(
                        text = "40.0+ (Max)",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = DbBorderBlack
                    )
                }
            }

            Spacer(modifier = Modifier.height(2.dp))

            // Comic Advice Box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.surface)
                    .border(2.dp, DbBorderBlack, RoundedCornerShape(12.dp))
                    .padding(14.dp)
            ) {
                Text(
                    text = category.advice,
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    color = DbBorderBlack
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun IMCScreenPreview() {
    BMIJetpackComposerTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { IMCTopBar() }
        ) { innerPadding ->
            IMCScreen(modifier = Modifier.padding(innerPadding))
        }
    }
}

@Preview
@Composable
fun BMIScreen() {
    IMCScreen()
}
