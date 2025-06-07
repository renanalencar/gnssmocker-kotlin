package br.com.renanalencar.gnssmocker.data.repository

import android.content.Context
import br.com.renanalencar.gnssmocker.data.local.AppDatabase
import br.com.renanalencar.gnssmocker.data.model.LocationPoint
import br.com.renanalencar.gnssmocker.data.model.Route
import br.com.renanalencar.gnssmocker.domain.repository.RouteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RouteRepositoryRoomImpl(
    context: Context,
) : RouteRepository {
    private val dao = AppDatabase.getInstance(context).routeDao()

    override suspend fun saveRoute(route: Route) =
        withContext(Dispatchers.IO) {
            val points =
                route.points.map {
                    br.com.renanalencar.gnssmocker.data.local.entity.LocationPointEntity(
                        routeId = 0,
                        latitude = it.latitude,
                        longitude = it.longitude,
                    )
                }
            dao.insertRouteWithPoints(route.name, points)
        }

    override suspend fun getAllRoutes(): List<Route> =
        withContext(Dispatchers.IO) {
            dao.getAllRoutes().map { entity ->
                val points =
                    dao.getPointsForRoute(entity.id).map {
                        LocationPoint(it.latitude, it.longitude)
                    }
                Route(name = entity.name, points = points)
            }
        }

    override suspend fun deleteRoute(name: String) =
        withContext(Dispatchers.IO) {
            val route = dao.getAllRoutes().find { it.name == name } ?: return@withContext
            dao.deleteRouteWithPoints(route)
        }

    override suspend fun renameRoute(
        oldName: String,
        newName: String,
    ) {
        val entity = dao.getAllRoutes().find { it.name == oldName } ?: return
        dao.updateRouteName(entity.id, newName)
    }
}
