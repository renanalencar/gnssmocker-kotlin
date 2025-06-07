package br.com.renanalencar.gnssmocker.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.renanalencar.gnssmocker.data.model.Route
import br.com.renanalencar.gnssmocker.ui.component.EditRouteNameDialog

@Composable
fun RouteListScreen(
    routes: List<Route> = emptyList(), // Substituir por StateFlow ou ViewModel
    onSimulate: (Route) -> Unit,
    onFixRoute: (Route) -> Unit,
) {
    var editingRoute by remember { mutableStateOf<Route?>(null) }

    editingRoute?.let {
        EditRouteNameDialog(
            currentName = it.name,
            onRename = { newName ->
                onRenameRoute(it.name, newName)
                editingRoute = null
            },
            onDismiss = { editingRoute = null },
        )
    }

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Rotas Salvas", style = MaterialTheme.typography.titleLarge)

        Spacer(Modifier.height(16.dp))

        LazyColumn {
            items(routes) { route ->
                Card(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .clickable { onSimulate(route) },
                ) {
                    Column(Modifier.padding(16.dp)) {
                        Text(route.name, style = MaterialTheme.typography.titleMedium)
                        Spacer(Modifier.height(8.dp))
                        Text("Pontos: ${route.points.size}")
                        Row(
                            horizontalArrangement = Arrangement.End,
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            TextButton(onClick = { onFixRoute(route) }) {
                                Text("Fixar")
                            }
                            TextButton(onClick = { onSimulate(route) }) {
                                Text("Simular")
                            }
                            TextButton(onClick = { editingRoute = route }) {
                                Text("Renomear")
                            }
                        }
                    }
                }
            }
        }
    }
}
