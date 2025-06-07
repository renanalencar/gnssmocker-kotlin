package br.com.renanalencar.gnssmocker.ui.screen

import android.app.Activity
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteActivity
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode

@Composable
fun SearchAddressScreen(
    onAddressSelected: (latitude: Double, longitude: Double, name: String) -> Unit,
    onBack: () -> Unit,
) {
    val context = LocalContext.current

    // Inicializa o Places uma vez
    LaunchedEffect(Unit) {
        if (!Places.isInitialized()) {
            Places.initialize(context, "SUA_API_KEY_AQUI")
        }
    }

    var selectedPlace by remember { mutableStateOf<Place?>(null) }

    val launcher =
        rememberLauncherForActivityResult(StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val place = Autocomplete.getPlaceFromIntent(result.data!!)
                selectedPlace = place
                place.latLng?.let {
                    onAddressSelected(it.latitude, it.longitude, place.name ?: "Local")
                }
            } else if (result.resultCode == AutocompleteActivity.RESULT_ERROR) {
                val status = Autocomplete.getStatusFromIntent(result.data!!)
                Log.e("Search", "Erro: ${status.statusMessage}")
            }
        }

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Pesquisar endereço", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(16.dp))

        Button(
            onClick = {
                val fields = listOf(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG)
                val intent =
                    Autocomplete
                        .IntentBuilder(AutocompleteActivityMode.OVERLAY, fields)
                        .build(context)
                launcher.launch(intent)
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Abrir pesquisa de endereço")
        }

        selectedPlace?.let { place ->
            Spacer(Modifier.height(16.dp))
            Text("Endereço selecionado:")
            Text(place.name ?: "")
            Text("Lat: ${place.latLng?.latitude}, Lng: ${place.latLng?.longitude}")
        }

        Spacer(Modifier.weight(1f))
        Button(onClick = onBack, modifier = Modifier.fillMaxWidth()) {
            Text("Voltar")
        }
    }
}
