package br.com.renanalencar.gnssmocker.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import br.com.renanalencar.gnssmocker.data.local.entity.LocationPointEntity
import br.com.renanalencar.gnssmocker.data.local.entity.RouteEntity

@Dao
interface RouteDao {
    @Insert
    suspend fun insertRoute(route: RouteEntity): Long

    @Insert
    suspend fun insertPoints(points: List<LocationPointEntity>)

    @Transaction
    suspend fun insertRouteWithPoints(
        name: String,
        points: List<LocationPointEntity>,
    ) {
        val routeId = insertRoute(RouteEntity(name = name))
        val updatedPoints = points.map { it.copy(routeId = routeId) }
        insertPoints(updatedPoints)
    }

    @Query("SELECT * FROM routes")
    suspend fun getAllRoutes(): List<RouteEntity>

    @Query("SELECT * FROM points WHERE routeId = :routeId")
    suspend fun getPointsForRoute(routeId: Long): List<LocationPointEntity>

    @Query("UPDATE routes SET name = :newName WHERE id = :routeId")
    suspend fun updateRouteName(
        routeId: Long,
        newName: String,
    )

    @Delete
    suspend fun deleteRoute(route: RouteEntity)

    @Query("DELETE FROM points WHERE routeId = :routeId")
    suspend fun deletePointsByRoute(routeId: Long)

    @Transaction
    suspend fun deleteRouteWithPoints(route: RouteEntity) {
        deletePointsByRoute(route.id)
        deleteRoute(route)
    }
}
