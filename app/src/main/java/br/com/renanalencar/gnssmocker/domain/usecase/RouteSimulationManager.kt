package br.com.renanalencar.gnssmocker.domain.usecase

import android.content.Context
import android.location.Location
import android.os.Environment
import android.os.SystemClock
import br.com.renanalencar.gnssmocker.data.model.GnssSettings
import br.com.renanalencar.gnssmocker.data.model.LocationPoint
import br.com.renanalencar.gnssmocker.util.SimulationLogger
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.File
import kotlin.math.pow
import kotlin.math.round

class RouteSimulationManager {
    fun simulateRoute(
        points: List<LocationPoint>,
        speedMultiplier: Float = 1.0f,
        repeatMode: RepeatMode = RepeatMode.STOP,
        settings: GnssSettings,
    ): Flow<Location> =
        flow {
            var direction = 1
            var index = 0
            val delayMs = (1000L / speedMultiplier).toLong()

            while (true) {
                if (index in points.indices) {
                    val point = points[index]
                    val latLng = applyTruncation(point, settings.truncateCoordinates)

                    val location =
                        Location("gnss-mocker").apply {
                            latitude = latLng.latitude
                            longitude = latLng.longitude
                            accuracy = settings.gnssAccuracy
                            altitude = settings.altitude.toDouble()
                            time = System.currentTimeMillis()
                            elapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos()
                        }

                    emit(location)
                    index += direction
                } else {
                    when (repeatMode) {
                        RepeatMode.STOP -> break
                        RepeatMode.RESTART -> index = 0
                        RepeatMode.REVERSE -> {
                            direction *= -1
                            index += direction
                        }
                    }
                }
                delay(delayMs)
            }
        }

    private fun applyTruncation(
        point: LocationPoint,
        truncate: Boolean,
    ): LatLng =
        if (truncate) {
            LatLng(
                truncateDecimals(point.latitude, 6),
                truncateDecimals(point.longitude, 6),
            )
        } else {
            LatLng(point.latitude, point.longitude)
        }

    private fun truncateDecimals(
        value: Double,
        digits: Int,
    ): Double {
        val factor = 10.0.pow(digits)
        return round(value * factor) / factor
    }

    fun exportLogToFile(
        context: Context,
        asJson: Boolean = true,
    ): File {
        val exportDir = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
        val fileName = if (asJson) "gnss_log.json" else "gnss_log.csv"
        val file = File(exportDir, fileName)

        val content =
            if (asJson) {
                SimulationLogger.exportAsJson()
            } else {
                SimulationLogger.exportAsCsv()
            }

        file.writeText(content)
        return file
    }
}

enum class RepeatMode {
    STOP,
    REVERSE,
    RESTART,
}
