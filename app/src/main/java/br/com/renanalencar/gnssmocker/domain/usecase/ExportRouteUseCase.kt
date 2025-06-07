package br.com.renanalencar.gnssmocker.domain.usecase

import android.content.Context
import android.net.Uri
import br.com.renanalencar.gnssmocker.domain.repository.RouteRepository
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ExportRoutesUseCase(
    private val context: Context,
    private val repository: RouteRepository,
) {
    suspend fun execute(fileUri: Uri) {
        val routes = repository.getAllRoutes()
        val json = Json.encodeToString(routes)

        context.contentResolver.openOutputStream(fileUri)?.bufferedWriter()?.use {
            it.write(json)
        } ?: throw Exception("Erro ao escrever no arquivo")
    }
}
