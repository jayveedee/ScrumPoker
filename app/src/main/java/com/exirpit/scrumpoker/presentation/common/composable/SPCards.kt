package com.exirpit.scrumpoker.presentation.common.composable

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.exirpit.scrumpoker.domain.model.card.Card
import com.exirpit.scrumpoker.presentation.common.theme.ScrumPokerShapes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScrumPokerGridCard(
    modifier: Modifier = Modifier,
    card: Card,
    onExpandedStateChanged: () -> Unit
) {
    Card(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(140.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp,
            pressedElevation = 5.dp
        ),
        shape = ScrumPokerShapes.large,
        onClick = {
            onExpandedStateChanged.invoke()
        }
    ) {
        CardFrontSide(
            cardTitle = card.title,
            cardFontSize = 40.sp,
            cardFontWeight = FontWeight.Bold
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScrumPokerExpandedCard(
    modifier: Modifier = Modifier,
    card: Card,
    usesGyroScope: Boolean = true,
    onExpandedStateChanged: () -> Unit
) {
    var isFlipped by remember { mutableStateOf(false) }

    var yAxis by remember { mutableFloatStateOf(0f) }

    if (usesGyroScope) {
        val sensorManager = LocalContext.current.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val gyroscopeSensor = remember { sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE) }

        val sensorEventListener = object : SensorEventListener {

            override fun onSensorChanged(p0: SensorEvent?) {
                yAxis = if (p0 != null) p0.values[1] else 0f
            }

            override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
                // Do nothing
            }
        }

        DisposableEffect(sensorManager, gyroscopeSensor) {
            // Register the sensor listener when the Composable is created
            sensorManager.registerListener(sensorEventListener, gyroscopeSensor, SensorManager.SENSOR_DELAY_GAME)

            onDispose {
                // Unregister the sensor listener when the Composable is disposed
                sensorManager.unregisterListener(sensorEventListener)
            }
        }
    }

    Card(
        modifier = modifier
            .padding(25.dp)
            .fillMaxWidth()
            .graphicsLayer(rotationX = 0f, rotationY = yAxis, rotationZ = 0f),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp,
            pressedElevation = 5.dp
        ),
        shape = ScrumPokerShapes.large,
        onClick = {
            if (isFlipped) {
                onExpandedStateChanged.invoke()
            }
            isFlipped = !isFlipped
        }
    ) {
        if (!isFlipped) {
            CardBackSide()
        } else {
            CardFrontSide(cardTitle = card.title, cardFontSize = 150.sp)
        }
    }
}

@Composable
private fun CardFrontSide(
    cardTitle: String,
    cardFontSize: TextUnit = 30.sp,
    cardFontWeight: FontWeight = FontWeight.Bold,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = cardTitle,
            textAlign = TextAlign.Center,
            fontSize = cardFontSize,
            fontWeight = cardFontWeight,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
private fun CardBackSide() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.tertiary),
        contentAlignment = Alignment.Center,
    ) {

    }
}