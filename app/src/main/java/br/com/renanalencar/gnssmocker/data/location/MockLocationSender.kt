package br.com.renanalencar.gnssmocker.data.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MockLocationSender(
    context: Context,
) {
    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    @SuppressLint("MissingPermission")
    suspend fun sendMockLocation(location: Location) =
        withContext(Dispatchers.IO) {
            fusedLocationClient.setMockMode(true)
            fusedLocationClient.setMockLocation(location)
        }
}
