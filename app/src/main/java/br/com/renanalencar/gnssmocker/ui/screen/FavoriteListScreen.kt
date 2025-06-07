package br.com.renanalencar.gnssmocker.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.renanalencar.gnssmocker.domain.model.FavoriteLocation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteListScreen(
    favorites: List<FavoriteLocation>,
    onSelect: (FavoriteLocation) -> Unit,
    onBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Favoritos") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, null)
                    }
                },
            )
        },
    ) { padding ->
        LazyColumn(Modifier.padding(padding)) {
            items(favorites) { fav ->
                ListItem(
                    headlineText = { Text(fav.name) },
                    supportingText = { Text("%.6f, %.6f".format(fav.latitude, fav.longitude)) },
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .clickable { onSelect(fav) }
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                )
                Divider()
            }
        }
    }
}
