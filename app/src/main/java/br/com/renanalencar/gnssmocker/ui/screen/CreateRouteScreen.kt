package br.com.renanalencar.gnssmocker.ui.screen

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import br.com.renanalencar.gnssmocker.data.model.LocationPoint
import br.com.renanalencar.gnssmocker.ui.viewmodel.MainViewModel

@Composable
fun CreateRouteScreen(
    viewModel: MainViewModel,
    context: Context,
    onSaved: () -> Unit,
) {
    val routePoints = remember { mutableStateListOf<LocationPoint>() }
    var showSaveDialog by remember { mutableStateOf(false) }
    var suggestedName by remember { mutableStateOf("") }

    if (showSaveDialog) {
        SaveRouteDialog(
            defaultName = suggestedName,
            onConfirm = { name ->
                viewModel.saveNewRoute(name, routePoints.toList())
                showSaveDialog = false
                onSaved()
            },
            onDismiss = { showSaveDialog = false },
        )
    }

    // Mapa e traçado omitidos por brevidade

    FloatingActionButton(onClick = {
        if (routePoints.isNotEmpty()) {
            viewModel.suggestRouteNameFromFirstPoint(context, routePoints.first()) { name ->
                suggestedName = name
                showSaveDialog = true
            }
        }
    }) {
        Icon(Icons.Default.Save, contentDescription = "Salvar rota")
    }
}
