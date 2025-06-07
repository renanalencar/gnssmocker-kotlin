package br.com.renanalencar.gnssmocker.data.repository

import android.content.Context
import br.com.renanalencar.gnssmocker.data.local.AppDatabase
import br.com.renanalencar.gnssmocker.data.local.entity.FavoriteLocationEntity
import br.com.renanalencar.gnssmocker.domain.model.FavoriteLocation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FavoriteRepository(
    context: Context,
) {
    private val dao = AppDatabase.getInstance(context).favoriteDao()

    suspend fun addFavorite(fav: FavoriteLocation) =
        withContext(Dispatchers.IO) {
            dao.insert(FavoriteLocationEntity(name = fav.name, latitude = fav.latitude, longitude = fav.longitude))
        }

    suspend fun getFavorites(): List<FavoriteLocation> =
        withContext(Dispatchers.IO) {
            dao.getAll().map {
                FavoriteLocation(name = it.name, latitude = it.latitude, longitude = it.longitude)
            }
        }

    suspend fun deleteFavorite(fav: FavoriteLocation) =
        withContext(Dispatchers.IO) {
            val entity = FavoriteLocationEntity(0, fav.name, fav.latitude, fav.longitude)
            dao.delete(entity)
        }
}
