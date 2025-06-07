package br.com.renanalencar.gnssmocker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import br.com.renanalencar.gnssmocker.data.local.DataStoreManager
import br.com.renanalencar.gnssmocker.ui.navigation.AppNavHost
import br.com.renanalencar.gnssmocker.ui.theme.GNSSMockerTheme
import br.com.renanalencar.gnssmocker.ui.viewmodel.MainViewModel
import br.com.renanalencar.gnssmocker.ui.viewmodel.SettingsViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataStore = DataStoreManager(this)

        enableEdgeToEdge()
        setContent {
            val mainViewModel: MainViewModel = viewModel()
            val settingsViewModel: SettingsViewModel = viewModel()
            val theme =
                settingsViewModel.settings
                    .collectAsState()
                    .value.theme

            GNSSMockerTheme(
                darkTheme =
                    when (theme) {
                        "light" -> false
                        "dark" -> true
                        else -> isSystemInDarkTheme()
                    },
            ) {
                val navController = rememberNavController()

                AppNavHost(
                    navController = navController,
                    mainViewModel = mainViewModel,
                    settingsViewModel = settingsViewModel,
                )
            }
        }
    }
}
