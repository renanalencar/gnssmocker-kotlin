package br.com.renanalencar.gnssmocker.data.local

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.userPrefs by preferencesDataStore("user_prefs")

class UserPreferencesDataStore(
    private val context: Context,
) {
    companion object {
        val LAST_ROUTE_NAME = stringPreferencesKey("last_route_name")
        val LAST_ADDRESS = stringPreferencesKey("last_address")
        val LAST_LAT = doublePreferencesKey("last_lat")
        val LAST_LNG = doublePreferencesKey("last_lng")
    }

    val lastRouteName: Flow<String?> = context.userPrefs.data.map { it[LAST_ROUTE_NAME] }
    val lastAddress: Flow<String?> = context.userPrefs.data.map { it[LAST_ADDRESS] }
    val lastLatLng: Flow<Pair<Double, Double>?> =
        context.userPrefs.data.map {
            val lat = it[LAST_LAT]
            val lng = it[LAST_LNG]
            if (lat != null && lng != null) Pair(lat, lng) else null
        }

    suspend fun saveLastRoute(name: String) {
        context.userPrefs.edit { it[LAST_ROUTE_NAME] = name }
    }

    suspend fun saveLastAddress(address: String) {
        context.userPrefs.edit { it[LAST_ADDRESS] = address }
    }

    suspend fun saveLastLocation(
        lat: Double,
        lng: Double,
    ) {
        context.userPrefs.edit {
            it[LAST_LAT] = lat
            it[LAST_LNG] = lng
        }
    }

    suspend fun clear() {
        context.userPrefs.edit { it.clear() }
    }
}
