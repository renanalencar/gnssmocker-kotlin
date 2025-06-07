package br.com.renanalencar.gnssmocker.domain.repository

import br.com.renanalencar.gnssmocker.data.model.Route

interface RouteRepository {
    suspend fun saveRoute(route: Route)

    suspend fun getAllRoutes(): List<Route>

    suspend fun deleteRoute(name: String)

    suspend fun renameRoute(
        oldName: String,
        newName: String,
    )
}
