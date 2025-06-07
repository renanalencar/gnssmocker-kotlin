package br.com.renanalencar.gnssmocker.data.local

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("app_prefs")

class DataStoreManager(
    private val context: Context,
) {
    companion object {
        val DisclaimerAcceptedKey = booleanPreferencesKey("disclaimer_accepted")
    }

    val isDisclaimerAccepted =
        context.dataStore.data.map { prefs ->
            prefs[DisclaimerAcceptedKey] ?: false
        }

    suspend fun setDisclaimerAccepted() {
        context.dataStore.edit { prefs ->
            prefs[DisclaimerAcceptedKey] = true
        }
    }
}
