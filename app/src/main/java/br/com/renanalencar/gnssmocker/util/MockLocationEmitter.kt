package br.com.renanalencar.gnssmocker.util

import android.location.Location
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

object MockLocationEmitter {
    private val _locationFlow = MutableSharedFlow<Location>(extraBufferCapacity = 1)
    val locationFlow: SharedFlow<Location> = _locationFlow.asSharedFlow()

    fun emit(location: Location) {
        _locationFlow.tryEmit(location)
    }
}
