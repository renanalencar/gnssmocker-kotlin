package br.com.renanalencar.gnssmocker.data.location

import android.content.Context
import android.location.Address
import android.location.Geocoder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Locale

object GeocoderHelper {
    suspend fun getAddressFromCoordinates(
        context: Context,
        latitude: Double,
        longitude: Double,
    ): String? =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val geocoder = Geocoder(context, Locale.getDefault())
                val results: List<Address> = geocoder.getFromLocation(latitude, longitude, 1)
                if (results.isNotEmpty()) {
                    val address = results[0]
                    buildString {
                        append(address.thoroughfare ?: "") // rua
                        if (address.subThoroughfare != null) append(", ${address.subThoroughfare}")
                        if (address.locality != null) append(" - ${address.locality}")
                        if (address.adminArea != null) append(" - ${address.adminArea}")
                        if (address.countryName != null) append(" - ${address.countryName}")
                    }
                } else {
                    null
                }
            } catch (e: Exception) {
                null
            }
        }
}
