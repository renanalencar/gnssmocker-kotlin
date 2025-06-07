package br.com.renanalencar.gnssmocker.data.model

import kotlinx.serialization.Serializable

@Serializable
data class LocationPoint(
    val latitude: Double,
    val longitude: Double,
)

@Serializable
data class Route(
    val name: String,
    val points: List<LocationPoint>,
)
