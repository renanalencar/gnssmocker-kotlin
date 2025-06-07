package br.com.renanalencar.gnssmocker.ui.screen

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.PowerManager
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import br.com.renanalencar.gnssmocker.data.local.DataStoreManager
import br.com.renanalencar.gnssmocker.ui.component.DisclaimerDialog
import kotlinx.coroutines.launch

@Composable
fun FirstAccessScreen(onReady: () -> Unit) {
    val context = LocalContext.current
    val dataStore = remember { DataStoreManager(context) }
    val coroutineScope = rememberCoroutineScope()

    val notificationLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission(),
        ) { granted -> /* handle result */ }

    var showDialog by remember { mutableStateOf(true) }

    if (showDialog) {
        DisclaimerDialog(onConfirm = {
            coroutineScope.launch {
                dataStore.setDisclaimerAccepted()
                showDialog = false
            }
        })
    } else {
        PermissionChecklist(context = context, onAllPermissionsHandled = onReady, requestNotification = {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                notificationLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
            }
        })
    }
}

@Composable
fun PermissionChecklist(
    context: Context,
    onAllPermissionsHandled: () -> Unit,
    requestNotification: () -> Unit,
) {
    val powerManager = context.getSystemService(Context.POWER_SERVICE) as PowerManager
    Column(Modifier.padding(16.dp)) {
        Text("Permissões necessárias", style = MaterialTheme.typography.titleLarge)

        Spacer(Modifier.height(16.dp))

        Button(onClick = {
            context.startActivity(Intent(Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS))
        }) {
            Text("Ativar modo Desenvolvedor / Definir App como Mock")
        }

        Spacer(Modifier.height(8.dp))

        Button(onClick = {
            val intent = Intent(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS)
            context.startActivity(intent)
        }) {
            Text("Desativar otimização de bateria")
        }

        Spacer(Modifier.height(8.dp))

        Button(onClick = requestNotification) {
            Text("Permitir notificações")
        }

        Spacer(Modifier.height(24.dp))

        Button(onClick = onAllPermissionsHandled) {
            Text("Permissões concedidas")
        }
    }
}
