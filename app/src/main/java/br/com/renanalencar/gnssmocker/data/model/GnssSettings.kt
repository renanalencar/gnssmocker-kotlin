package br.com.renanalencar.gnssmocker.data.model

data class GnssSettings(
    val theme: String,
    val mapType: String,
    val distanceUnit: String,
    val stopFromNotification: Boolean,
    val startWithLastRoute: Boolean,
    val hideNotificationOnLock: Boolean,
    val useAnimation: Boolean,
    val usePlayServices: Boolean,
    val truncateCoordinates: Boolean,
    val altitude: Float,
    val gnssAccuracy: Float,
)
