package br.com.renanalencar.gnssmocker.util

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service.START_STICKY
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.Job

@AndroidEntryPoint
class MockLocationService : LifecycleService() {
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var job: Job? = null

    override fun onCreate() {
        super.onCreate()
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        startForegroundService()
    }

    override fun onStartCommand(
        intent: Intent?,
        flags: Int,
        startId: Int,
    ): Int {
        startMockLocationUpdates()
        return START_STICKY
    }

    override fun onDestroy() {
        job?.cancel()
        super.onDestroy()
    }

    private fun startForegroundService() {
        val channelId = "mock_location_channel"
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O &&
            notificationManager.getNotificationChannel(channelId) == null
        ) {
            val channel =
                NotificationChannel(
                    channelId,
                    "Mock Location Service",
                    NotificationManager.IMPORTANCE_LOW,
                )
            notificationManager.createNotificationChannel(channel)
        }

        val notification =
            NotificationCompat
                .Builder(this, channelId)
                .setContentTitle("Mock GNSS Ativo")
                .setContentText("A localização está sendo simulada.")
                .setSmallIcon(R.drawable.ic_location_on)
                .build()

        startForeground(1, notification)
    }

    @SuppressLint("MissingPermission")
    private fun startMockLocationUpdates() {
        job =
            lifecycleScope.launch {
                MockLocationEmitter.locationFlow.collect { location ->
                    fusedLocationClient.setMockMode(true)
                    fusedLocationClient.setMockLocation(location)
                }
            }
    }
}
