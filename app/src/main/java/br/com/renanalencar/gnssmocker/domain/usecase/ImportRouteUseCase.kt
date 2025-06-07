package br.com.renanalencar.gnssmocker.domain.usecase

import android.content.Context
import android.net.Uri
import br.com.renanalencar.gnssmocker.data.model.Route
import br.com.renanalencar.gnssmocker.domain.repository.RouteRepository
import kotlinx.serialization.json.Json

class ImportRoutesUseCase(
    private val context: Context,
    private val repository: RouteRepository,
) {
    suspend fun execute(fileUri: Uri): List<Route> {
        val input =
            context.contentResolver
                .openInputStream(fileUri)
                ?.bufferedReader()
                ?.use { it.readText() }
                ?: throw Exception("Erro ao ler arquivo")

        val routes = Json.decodeFromString<List<Route>>(input)
        routes.forEach { repository.saveRoute(it) }
        return routes
    }
}
