package br.com.renanalencar.gnssmocker.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.renanalencar.gnssmocker.data.model.GnssSettings
import br.com.renanalencar.gnssmocker.data.model.Route
import br.com.renanalencar.gnssmocker.domain.usecase.RepeatMode
import br.com.renanalencar.gnssmocker.util.RouteDistanceCalculator
import kotlin.math.roundToInt

@Composable
fun RouteConfigurationDialog(
    route: Route,
    settings: GnssSettings,
    onDismiss: () -> Unit,
    onStartSimulation: (speed: Float, mode: RepeatMode) -> Unit,
) {
    var speed by remember { mutableStateOf(1.0f) }
    var repeatMode by remember { mutableStateOf(RepeatMode.STOP) }

    val isMetric = settings.distanceUnit == "km" || (settings.distanceUnit == "auto")
    val distanceMeters = RouteDistanceCalculator.calculateTotalDistance(route.points)
    val distanceDisplay =
        if (isMetric) {
            "%.2f km".format(distanceMeters / 1000.0)
        } else {
            "%.2f mi".format(distanceMeters / 1609.34)
        }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Configurar simulação") },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text("Velocidade: ${speed.roundToInt()}x")
                Slider(
                    value = speed,
                    onValueChange = { speed = it },
                    valueRange = 0.5f..5f,
                    steps = 8,
                )

                Spacer(modifier = Modifier.height(12.dp))
                Text("Modo de repetição:")
                RepeatMode.entries.forEach { mode ->
                    Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                        RadioButton(
                            selected = repeatMode == mode,
                            onClick = { repeatMode = mode },
                        )
                        Text(mode.name.lowercase().replaceFirstChar(Char::titlecase))
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))
                Text("Distância total: $distanceDisplay")
            }
        },
        confirmButton = {
            TextButton(onClick = {
                onStartSimulation(speed, repeatMode)
            }) {
                Text("Iniciar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        },
    )
}
