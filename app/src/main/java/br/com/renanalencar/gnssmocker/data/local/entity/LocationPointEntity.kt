package br.com.renanalencar.gnssmocker.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "points",
    foreignKeys = [
        ForeignKey(
            entity = RouteEntity::class,
            parentColumns = ["id"],
            childColumns = ["routeId"],
            onDelete = ForeignKey.CASCADE,
        ),
    ],
)
data class LocationPointEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val routeId: Long,
    val latitude: Double,
    val longitude: Double,
)
