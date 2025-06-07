package br.com.renanalencar.gnssmocker.util

import android.location.Location
import com.google.gson.Gson

object SimulationLogger {
    private val logEntries = mutableListOf<LocationLogEntry>()

    fun add(location: Location) {
        logEntries.add(
            LocationLogEntry(
                timestamp = System.currentTimeMillis(),
                latitude = location.latitude,
                longitude = location.longitude,
                altitude = location.altitude,
                accuracy = location.accuracy,
            ),
        )
    }

    fun clear() = logEntries.clear()

    fun exportAsJson(): String = Gson().toJson(logEntries)

    fun exportAsCsv(): String {
        val header = "timestamp,latitude,longitude,altitude,accuracy"
        val rows =
            logEntries.joinToString("\n") {
                "${it.timestamp},${it.latitude},${it.longitude},${it.altitude ?: ""},${it.accuracy ?: ""}"
            }
        return "$header\n$rows"
    }
}
