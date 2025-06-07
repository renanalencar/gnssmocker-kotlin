package br.com.renanalencar.gnssmocker.util

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.os.SystemClock

class MockLocationProvider(
    private val context: Context,
) {
    private val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    private val providerName = LocationManager.GPS_PROVIDER

    @SuppressLint("MissingPermission")
    fun pushLocation(location: Location) {
        if (!locationManager.isProviderEnabled(providerName)) return

        val mockLocation =
            Location(providerName).apply {
                latitude = location.latitude
                longitude = location.longitude
                altitude = location.altitude
                accuracy = location.accuracy
                time = System.currentTimeMillis()
                elapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos()
                bearing = location.bearing
                speed = location.speed
            }

        try {
            locationManager.setTestProviderLocation(providerName, mockLocation)
        } catch (e: IllegalArgumentException) {
            // Setup provider if it doesn't exist
            try {
                locationManager.addTestProvider(
                    providerName,
                    false,
                    false,
                    false,
                    false,
                    true,
                    true,
                    true,
                    0,
                    5,
                )
                locationManager.setTestProviderEnabled(providerName, true)
                locationManager.setTestProviderLocation(providerName, mockLocation)
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }

    fun shutdown() {
        try {
            locationManager.removeTestProvider(providerName)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
