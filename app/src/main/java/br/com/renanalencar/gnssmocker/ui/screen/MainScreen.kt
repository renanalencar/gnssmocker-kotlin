package br.com.renanalencar.gnssmocker.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import br.com.renanalencar.gnssmocker.data.model.GnssSettings
import br.com.renanalencar.gnssmocker.ui.viewmodel.MainViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel,
    settings: GnssSettings,
    onCreateRouteClick: () -> Unit,
    onOpenSavedRoutes: () -> Unit,
    onOpenSettings: () -> Unit,
    onSearchAddress: () -> Unit,
) {
    val location by viewModel.currentLocation.collectAsState()
    val fullLocation by viewModel.currentLocationFull.collectAsState()
    val simulating by viewModel.simulating.collectAsState()

    val cameraPositionState =
        rememberCameraPositionState {
            position =
                CameraPosition.fromLatLngZoom(
                    LatLng(location.latitude, location.longitude),
                    17f,
                )
        }

    val mapType =
        when (settings.mapType) {
            "satellite" -> MapType.SATELLITE
            "terrain" -> MapType.TERRAIN
            else -> MapType.NORMAL
        }

    val address by viewModel.currentAddress.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(viewModel.currentLocation.collectAsState().value) {
        viewModel.resolveAddress(context)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("GNSS Mocker") },
                actions = {
                    IconButton(onClick = onOpenSavedRoutes) {
                        Icon(Icons.Default.Folder, contentDescription = "Rotas")
                    }
                    IconButton(onClick = onOpenSettings) {
                        Icon(Icons.Default.Settings, contentDescription = "Configurações")
                    }
                    IconButton(onClick = { onSearchAddress }) {
                        Icon(Icons.Default.Search, contentDescription = "Pesquisar")
                    }
                },
            )
        },
        bottomBar = {
            Column {
                BottomAppBar {
                    IconButton(onClick = onCreateRouteClick) {
                        Icon(Icons.Default.Add, null)
                    }
                    IconButton(onClick = { viewModel.stopSimulation() }) {
                        Icon(Icons.Default.Stop, null)
                    }
                    IconButton(onClick = onOpenSavedRoutes) {
                        Icon(Icons.Default.List, null)
                    }
                    IconButton(onClick = {
                        cameraPositionState.move(CameraUpdateFactory.newLatLngZoom(location, 17f))
                    }) {
                        Icon(Icons.Default.MyLocation, null)
                    }
                }

                // 📍 Dados GNSS simulados
                fullLocation?.let { loc ->
                    Text(
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 4.dp),
                        style = MaterialTheme.typography.bodySmall,
                        text =
                            buildString {
                                append("Lat: %.6f, Lng: %.6f".format(loc.latitude, loc.longitude))
                                append("\nAlt: %.1fm | Acc: ±%.1fm".format(loc.altitude, loc.accuracy))
                                append("\nFixado em: ${java.text.SimpleDateFormat("HH:mm:ss").format(loc.time)}")
                            },
                    )
                }

                // 🏷 Endereço (se resolvido)
                address?.let {
                    Text(
                        text = it,
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 2.dp),
                        style = MaterialTheme.typography.labelSmall,
                    )
                }
            }
        },
    ) { padding ->
        GoogleMap(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(padding),
            cameraPositionState = cameraPositionState,
            mapType = mapType,
        ) {
            Marker(state = MarkerState(position = LatLng(location.latitude, location.longitude)))
        }
    }
}
