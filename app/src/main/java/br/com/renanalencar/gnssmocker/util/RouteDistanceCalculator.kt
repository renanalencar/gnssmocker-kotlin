package br.com.renanalencar.gnssmocker.util

import br.com.renanalencar.gnssmocker.data.model.LocationPoint
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

object RouteDistanceCalculator {
    private const val EARTH_RADIUS_METERS = 6371000.0

    fun calculateTotalDistance(points: List<LocationPoint>): Double {
        if (points.size < 2) return 0.0

        var total = 0.0
        for (i in 0 until points.size - 1) {
            total += haversine(points[i], points[i + 1])
        }
        return total
    }

    private fun haversine(
        p1: LocationPoint,
        p2: LocationPoint,
    ): Double {
        val dLat = Math.toRadians(p2.latitude - p1.latitude)
        val dLon = Math.toRadians(p2.longitude - p1.longitude)
        val lat1 = Math.toRadians(p1.latitude)
        val lat2 = Math.toRadians(p2.latitude)

        val a =
            sin(dLat / 2).pow(2.0) +
                cos(lat1) * cos(lat2) *
                sin(dLon / 2).pow(2.0)
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))

        return EARTH_RADIUS_METERS * c
    }
}
