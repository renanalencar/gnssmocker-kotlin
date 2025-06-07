package br.com.renanalencar.gnssmocker.ui.component

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun DisclaimerDialog(onConfirm: () -> Unit) {
    AlertDialog(
        onDismissRequest = {},
        title = { Text("Aviso Legal") },
        text = {
            Text(
                "Este aplicativo foi desenvolvido exclusivamente para auxiliar no desenvolvimento de software. O uso indevido pode violar os Termos de Uso do sistema Android.",
            )
        },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text("Concordo")
            }
        },
    )
}
