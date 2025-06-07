package br.com.renanalencar.gnssmocker.data.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LegacyMockLocationSender(
    private val context: Context,
) {
    private val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    private val provider = LocationManager.GPS_PROVIDER

    @SuppressLint("MissingPermission")
    suspend fun sendMockLocation(location: Location) =
        withContext(Dispatchers.IO) {
            if (!locationManager.isProviderEnabled(provider)) return@withContext

            try {
                if (!locationManager.allProviders.contains(provider)) {
                    locationManager.addTestProvider(
                        provider,
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
                    locationManager.setTestProviderEnabled(provider, true)
                }

                locationManager.setTestProviderLocation(provider, location)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
}
