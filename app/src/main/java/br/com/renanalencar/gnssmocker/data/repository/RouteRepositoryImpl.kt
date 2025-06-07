package br.com.renanalencar.gnssmocker.data.repository

import android.content.Context
import br.com.renanalencar.gnssmocker.data.model.Route
import br.com.renanalencar.gnssmocker.domain.repository.RouteRepository
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class RouteRepositoryImpl(
    private val context: Context,
) : RouteRepository {
    private val mutex = Mutex()
    private val routes = mutableListOf<Route>()

    override suspend fun saveRoute(route: Route) {
        mutex.withLock {
            routes.removeAll { it.name == route.name }
            routes.add(route)
        }
    }

    override suspend fun getAllRoutes(): List<Route> =
        mutex.withLock {
            routes.toList()
        }

    override suspend fun deleteRoute(name: String) {
        mutex.withLock {
            routes.removeAll { it.name == name }
        }
    }

    override suspend fun renameRoute(
        oldName: String,
        newName: String,
    ) {
        dao.getAllRoutes().find { it.name == oldName }?.let {
            dao.updateRouteName(it.id, newName)
        }
    }
}
