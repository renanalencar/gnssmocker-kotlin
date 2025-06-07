package br.com.renanalencar.gnssmocker.ui.viewmodel

import android.app.Application
import android.content.Context
import android.location.Location
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import br.com.renanalencar.gnssmocker.data.local.UserPreferencesDataStore
import br.com.renanalencar.gnssmocker.data.location.GeocoderHelper
import br.com.renanalencar.gnssmocker.data.location.MockLocationSender
import br.com.renanalencar.gnssmocker.data.model.GnssSettings
import br.com.renanalencar.gnssmocker.data.model.LocationPoint
import br.com.renanalencar.gnssmocker.data.model.Route
import br.com.renanalencar.gnssmocker.data.repository.FavoriteRepository
import br.com.renanalencar.gnssmocker.data.repository.RouteRepositoryRoomImpl
import br.com.renanalencar.gnssmocker.domain.model.FavoriteLocation
import br.com.renanalencar.gnssmocker.domain.repository.RouteRepository
import br.com.renanalencar.gnssmocker.domain.usecase.RepeatMode
import br.com.renanalencar.gnssmocker.domain.usecase.RepeatMode.STOP
import br.com.renanalencar.gnssmocker.domain.usecase.RouteSimulationManager
import br.com.renanalencar.gnssmocker.util.RouteDistanceCalculator
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlin.coroutines.jvm.internal.CompletedContinuation.context

class MainViewModel(
    application: Application,
) : AndroidViewModel(application) {
    val favoriteAddresses: StateFlow<List<FavoriteAddress>>
    private val _simulating = MutableStateFlow(false)
    val simulating: StateFlow<Boolean> = _simulating

    private var simulationJob: Job? = null
    private val simulationManager = RouteSimulationManager()

    private val repository: RouteRepository = RouteRepositoryRoomImpl(application)

    private val _savedRoutes = MutableStateFlow<List<Route>>(emptyList())
    val savedRoutes: StateFlow<List<Route>> = _savedRoutes

    private val _currentAddress = MutableStateFlow<String?>(null)
    val currentAddress: StateFlow<String?> = _currentAddress

    private val userPrefs = UserPreferencesDataStore(application)

    val lastUsedRouteName: StateFlow<String?> =
        userPrefs.lastRouteName.stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            null,
        )

    private val _currentLocation = MutableStateFlow<LatLng>(LatLng(-8.05, -34.88))
    val currentLocation: StateFlow<LatLng> = _currentLocation

    private val _currentLocationFull = MutableStateFlow<Location?>(null)
    val currentLocationFull: StateFlow<Location?> = _currentLocationFull

    private val mockSender = MockLocationSender(application) // ou LegacyMockLocationSender

    init {
        viewModelScope.launch {
            _savedRoutes.value = repository.getAllRoutes()
        }
    }

    private val favoriteRepo = FavoriteRepository(application)

    private val _favorites = MutableStateFlow<List<FavoriteLocation>>(emptyList())
    val favorites: StateFlow<List<FavoriteLocation>> = _favorites

    fun loadFavorites() {
        viewModelScope.launch {
            _favorites.value = favoriteRepo.getFavorites()
        }
    }

    fun addFavoriteFromCurrent(name: String) {
        val latLng = currentLocation.value
        viewModelScope.launch {
            favoriteRepo.addFavorite(FavoriteLocation(name, latLng.latitude, latLng.longitude))
            loadFavorites()
        }
    }

    fun persistSessionState(
        routeName: String,
        lat: Double,
        lng: Double,
        address: String?,
    ) {
        viewModelScope.launch {
            userPrefs.saveLastRoute(routeName)
            address?.let { userPrefs.saveLastAddress(it) }
            userPrefs.saveLastLocation(lat, lng)
        }
    }

    fun tryResumeLastRoute() {
        viewModelScope.launch {
            val name = userPrefs.lastRouteName.firstOrNull()
            val allRoutes = repository.getAllRoutes()
            val route = allRoutes.find { it.name == name }
            route?.let {
                startSimulation(it, speed = 1.0f, mode = RepeatMode.STOP, settings = settingsFlow.value)
            }
        }
    }

    fun resolveAddress(context: Context) {
        val latLng = currentLocation.value
        viewModelScope.launch {
            val address = GeocoderHelper.getAddressFromCoordinates(context, latLng.latitude, latLng.longitude)
            _currentAddress.value = address
        }
    }

    fun prepareSimulation(route: Route) {
        val distance = RouteDistanceCalculator.calculateTotalDistance(route.points)
        // pode ser usado para mostrar o RouteConfigurationDialog futuramente
        startSimulation(
            route,
            speed = 1.0f,
            mode = STOP,
            settings = TODO(),
        )
    }

    fun startSimulation(
        route: Route,
        speed: Float,
        mode: RepeatMode,
        settings: GnssSettings,
    ) {
        stopSimulation()

        simulationJob =
            viewModelScope.launch {
                _simulating.value = true
                simulationManager.simulateRoute(route.points, speed, mode, settings).collect { location ->
                    _currentLocation.value = LatLng(location.latitude, location.longitude)
                    _currentLocationFull.value = location

                    if (settings.usePlayServices) {
                        mockSender.sendMockLocation(location)
                    }
                }
                _simulating.value = false
            }
    }

    fun stopSimulation() {
        simulationJob?.cancel()
        _simulating.value = false
    }

    fun saveNewRoute(
        name: String,
        points: List<LocationPoint>,
    ) {
        val route = Route(name = name, points = points)
        viewModelScope.launch {
            repository.saveRoute(route)
            _savedRoutes.value = repository.getAllRoutes()
        }
    }

    fun clearAllRoutes() {
        viewModelScope.launch {
            _savedRoutes.value.forEach {
                repository.deleteRoute(it.name)
            }
            _savedRoutes.value = emptyList()
        }
    }

    fun fixRoute(route: Route) {
        // Marcar como fixa futuramente
    }

    fun moveToAddress(
        lat: Double,
        lng: Double,
        name: String,
    ) {
        _currentLocation.value = LatLng(lat, lng)
    }

    fun renameRoute(
        oldName: String,
        newName: String,
    ) {
        viewModelScope.launch {
            repository.renameRoute(oldName, newName)
            _savedRoutes.value = repository.getAllRoutes()
        }
    }

    fun suggestRouteNameFromFirstPoint(
        context: Context,
        firstPoint: LocationPoint,
        onNameReady: (String) -> Unit,
    ) {
        viewModelScope.launch {
            val address = GeocoderHelper.getAddressFromCoordinates(context, firstPoint.latitude, firstPoint.longitude)
            val name = address ?: "Rota ${System.currentTimeMillis()}"
            onNameReady(name)
        }
    }
}
