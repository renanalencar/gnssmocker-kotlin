package br.com.renanalencar.gnssmocker.ui.screen

import android.preference.PreferenceGroup
import android.preference.SwitchPreference
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.renanalencar.gnssmocker.data.local.SettingsDataStore
import br.com.renanalencar.gnssmocker.ui.component.PreferenceItem
import br.com.renanalencar.gnssmocker.ui.viewmodel.SettingsViewModel

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel,
    modifier: Modifier = Modifier,
) {
    val settings by viewModel.settings.collectAsState()

    Column(modifier.padding(16.dp)) {
        Text("Configurações", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(24.dp))

        PreferenceGroup(title = "Aparência") {
            PreferenceItem(
                title = "Tema",
                options = listOf("Padrão do sistema", "Escuro", "Claro"),
                selectedOption =
                    when (settings.theme) {
                        "light" -> "Claro"
                        "dark" -> "Escuro"
                        else -> "Padrão do sistema"
                    },
                onOptionSelected = {
                    val themeKey =
                        when (it) {
                            "Claro" -> "light"
                            "Escuro" -> "dark"
                            else -> "system"
                        }
                    viewModel.update(SettingsDataStore.THEME, themeKey)
                },
            )
        }

        PreferenceGroup("Mapa") {
            PreferenceItem(
                title = "Tipo de mapa",
                options = listOf("Normal", "Satélite", "Terreno"),
                selectedOption = settings.mapType.replaceFirstChar(Char::titlecase),
                onOptionSelected = {
                    viewModel.update(SettingsDataStore.MAP_TYPE, it.lowercase())
                },
            )
        }

        PreferenceGroup("Unidades de Distância") {
            PreferenceItem(
                title = "Unidade",
                options = listOf("Automático", "Quilômetros", "Milhas"),
                selectedOption =
                    when (settings.distanceUnit) {
                        "km" -> "Quilômetros"
                        "mi" -> "Milhas"
                        else -> "Automático"
                    },
                onOptionSelected = {
                    val unit =
                        when (it) {
                            "Quilômetros" -> "km"
                            "Milhas" -> "mi"
                            else -> "auto"
                        }
                    viewModel.update(SettingsDataStore.DISTANCE_UNIT, unit)
                },
            )
        }

        PreferenceGroup("Atalhos") {
            SwitchPreference("Parar simulação via notificação", settings.stopFromNotification) {
                viewModel.update(SettingsDataStore.STOP_VIA_NOTIFICATION, it)
            }
            SwitchPreference("Iniciar na última rota utilizada", settings.startWithLastRoute) {
                viewModel.update(SettingsDataStore.START_LAST_ROUTE, it)
            }
            SwitchPreference("Esconder notificações na tela de bloqueio", settings.hideNotificationOnLock) {
                viewModel.update(SettingsDataStore.HIDE_NOTIFICATIONS_LOCK, it)
            }
            SwitchPreference("Ativar animação no mapa", settings.useAnimation) {
                viewModel.update(SettingsDataStore.USE_ANIMATION, it)
            }
        }

        PreferenceGroup("Avançado") {
            SwitchPreference("Usar Google Play Location Services", settings.usePlayServices) {
                viewModel.update(SettingsDataStore.USE_PLAY_SERVICES, it)
            }
            SwitchPreference("Truncar coordenadas", settings.truncateCoordinates) {
                viewModel.update(SettingsDataStore.TRUNCATE_COORDINATES, it)
            }

            Spacer(Modifier.height(8.dp))
            Text("Altitude simulada: ${settings.altitude.toInt()}m")
            Slider(
                value = settings.altitude,
                onValueChange = { viewModel.update(SettingsDataStore.ALTITUDE, it) },
                valueRange = 0f..1000f,
            )

            Spacer(Modifier.height(8.dp))
            Text("Precisão GNSS: ${settings.gnssAccuracy.toInt()}m")
            Slider(
                value = settings.gnssAccuracy,
                onValueChange = { viewModel.update(SettingsDataStore.GNSS_ACCURACY, it) },
                valueRange = 1f..50f,
            )
        }

        PreferenceGroup("Sistema") {
            Button(
                onClick = { viewModel.reset() },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.errorContainer),
            ) {
                Text("Reiniciar configurações para padrão", color = MaterialTheme.colorScheme.onErrorContainer)
            }
        }
    }
}
