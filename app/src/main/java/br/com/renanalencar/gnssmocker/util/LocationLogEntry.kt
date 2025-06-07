package br.com.renanalencar.gnssmocker.util

data class LocationLogEntry(
    val timestamp: Long,
    val latitude: Double,
    val longitude: Double,
    val altitude: Double?,
    val accuracy: Float?,
)
