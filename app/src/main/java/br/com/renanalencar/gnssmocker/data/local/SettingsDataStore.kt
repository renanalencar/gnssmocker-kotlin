package br.com.renanalencar.gnssmocker.data.local

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import java.util.prefs.Preferences

private val Context.dataStore by preferencesDataStore("gnss_settings")

class SettingsDataStore(
    private val context: Context,
) {
    companion object {
        val THEME = stringPreferencesKey("theme") // system, light, dark
        val MAP_TYPE = stringPreferencesKey("map_type") // normal, satellite, terrain
        val DISTANCE_UNIT = stringPreferencesKey("distance_unit") // auto, km, mi
        val STOP_VIA_NOTIFICATION = booleanPreferencesKey("stop_from_notification")
        val START_LAST_ROUTE = booleanPreferencesKey("start_last_route")
        val HIDE_NOTIFICATIONS_LOCK = booleanPreferencesKey("hide_notifications_lock")
        val USE_ANIMATION = booleanPreferencesKey("use_animation")
        val USE_PLAY_SERVICES = booleanPreferencesKey("use_play_services")
        val TRUNCATE_COORDINATES = booleanPreferencesKey("truncate_coordinates")
        val ALTITUDE = floatPreferencesKey("altitude_meters")
        val GNSS_ACCURACY = floatPreferencesKey("gnss_accuracy_meters")
    }

    val settingsFlow =
        context.dataStore.data.map { prefs ->
            GnssSettings(
                theme = prefs[THEME] ?: "system",
                mapType = prefs[MAP_TYPE] ?: "normal",
                distanceUnit = prefs[DISTANCE_UNIT] ?: "auto",
                stopFromNotification = prefs[STOP_VIA_NOTIFICATION] ?: true,
                startWithLastRoute = prefs[START_LAST_ROUTE] ?: false,
                hideNotificationOnLock = prefs[HIDE_NOTIFICATIONS_LOCK] ?: false,
                useAnimation = prefs[USE_ANIMATION] ?: true,
                usePlayServices = prefs[USE_PLAY_SERVICES] ?: true,
                truncateCoordinates = prefs[TRUNCATE_COORDINATES] ?: false,
                altitude = prefs[ALTITUDE] ?: 0f,
                gnssAccuracy = prefs[GNSS_ACCURACY] ?: 5f,
            )
        }

    suspend fun updateSetting(
        key: Preferences.Key<*>,
        value: Any,
    ) {
        context.dataStore.edit { prefs ->
            when (key) {
                is Preferences.Key<String> -> prefs[key] = value as String
                is Preferences.Key<Boolean> -> prefs[key] = value as Boolean
                is Preferences.Key<Float> -> prefs[key] = value as Float
            }
        }
    }

    suspend fun resetToDefaults() {
        context.dataStore.edit { it.clear() }
    }
}
