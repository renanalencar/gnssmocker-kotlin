package br.com.renanalencar.gnssmocker.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import br.com.renanalencar.gnssmocker.domain.usecase.RepeatMode
import br.com.renanalencar.gnssmocker.ui.screen.CreateRouteScreen
import br.com.renanalencar.gnssmocker.ui.screen.FavoriteAddressesScreen
import br.com.renanalencar.gnssmocker.ui.screen.MainScreen
import br.com.renanalencar.gnssmocker.ui.screen.RouteListScreen
import br.com.renanalencar.gnssmocker.ui.screen.SearchAddressScreen
import br.com.renanalencar.gnssmocker.ui.screen.SettingsScreen
import br.com.renanalencar.gnssmocker.ui.viewmodel.MainViewModel
import br.com.renanalencar.gnssmocker.ui.viewmodel.SettingsViewModel

@Composable
fun AppNavHost(
    navController: NavHostController,
    mainViewModel: MainViewModel,
    settingsViewModel: SettingsViewModel,
) {
    NavHost(navController = navController, startDestination = Routes.MAIN) {
        composable(Routes.MAIN) {
            MainScreen(
                viewModel = mainViewModel,
                settings = settingsViewModel.settings.collectAsState().value,
                onCreateRouteClick = { navController.navigate(Routes.CREATE_ROUTE) },
                onOpenSavedRoutes = { navController.navigate(Routes.ROUTE_LIST) },
                onOpenSettings = { navController.navigate(Routes.SETTINGS) },
                onSearchAddress = { navController.navigate(Routes.SEARCH) },
            )
        }

        composable(Routes.CREATE_ROUTE) {
            CreateRouteScreen(
                onSave = {
                    mainViewModel.saveNewRoute(it)
                    navController.popBackStack()
                },
            )
        }

        composable(Routes.ROUTE_LIST) {
            RouteListScreen(
                routes = mainViewModel.savedRoutes.collectAsState().value,
                onSimulate = {
                    mainViewModel.prepareSimulation(it)
                    mainViewModel.startSimulation(
                        route = it,
                        speed = 1.0f,
                        mode = RepeatMode.STOP,
                        settings = settingsViewModel.settings.value,
                    )
                    navController.popBackStack()
                },
                onFixRoute = { mainViewModel.fixRoute(it) },
            )
        }

        composable(Routes.SETTINGS) {
            SettingsScreen(viewModel = settingsViewModel)
        }

        composable(Routes.SEARCH) {
            SearchAddressScreen(
                onAddressSelected = { lat, lng, name ->
                    mainViewModel.moveToAddress(lat, lng, name)
                    navController.popBackStack()
                },
                onBack = { navController.popBackStack() },
            )
        }

        composable(Routes.FAVORITES) {
            FavoriteAddressesScreen(
                viewModel = mainViewModel,
                onNavigateBack = { navController.popBackStack() },
            )
        }
    }
}
