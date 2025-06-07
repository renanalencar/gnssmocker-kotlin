package br.com.renanalencar.gnssmocker.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.renanalencar.gnssmocker.ui.viewmodel.MainViewModel

@Composable
fun FavoriteAddressesScreen(
    viewModel: MainViewModel,
    onNavigateBack: () -> Unit,
) {
    val favorites by viewModel.favoriteAddresses.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Endereços Favoritos") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                    }
                },
            )
        },
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(favorites) { address ->
                ListItem(
                    headlineContent = { Text(address.label ?: address.name) },
                    supportingContent = { Text("${address.lat}, ${address.lng}") },
                    trailingContent = {
                        IconButton(onClick = { viewModel.deleteFavoriteAddress(address) }) {
                            Icon(Icons.Default.Delete, contentDescription = "Remover")
                        }
                    },
                )
                Divider()
            }
        }
    }
}
