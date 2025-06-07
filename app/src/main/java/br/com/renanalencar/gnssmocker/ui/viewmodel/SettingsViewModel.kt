package br.com.renanalencar.gnssmocker.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import br.com.renanalencar.gnssmocker.data.local.SettingsDataStore
import br.com.renanalencar.gnssmocker.data.model.GnssSettings
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewModel(
    app: Application,
) : AndroidViewModel(app) {
    private val dataStore = SettingsDataStore(app)

    val settings: StateFlow<GnssSettings> =
        dataStore.settingsFlow
            .stateIn(viewModelScope, SharingStarted.Eagerly, GnssSettings("", "", "", true, false, false, true, true, false, 0f, 5f))

    fun <T : Any> update(
        key: androidx.datastore.preferences.core.Preferences.Key<T>,
        value: T,
    ) {
        viewModelScope.launch {
            dataStore.updateSetting(key, value)
        }
    }

    fun reset() {
        viewModelScope.launch {
            dataStore.resetToDefaults()
        }
    }
}
