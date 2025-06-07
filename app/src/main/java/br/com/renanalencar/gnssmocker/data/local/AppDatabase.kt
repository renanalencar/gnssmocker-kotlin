package br.com.renanalencar.gnssmocker.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.renanalencar.gnssmocker.data.local.dao.FavoriteDao
import br.com.renanalencar.gnssmocker.data.local.dao.RouteDao
import br.com.renanalencar.gnssmocker.data.local.entity.LocationPointEntity
import br.com.renanalencar.gnssmocker.data.local.entity.RouteEntity

@Database(entities = [RouteEntity::class, LocationPointEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun routeDao(): RouteDao

    abstract fun favoriteDao(): FavoriteDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room
                    .databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "gnss.db",
                    ).build()
                    .also { INSTANCE = it }
            }
    }
}
