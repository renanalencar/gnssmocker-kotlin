package br.com.renanalencar.gnssmocker.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import br.com.renanalencar.gnssmocker.data.local.entity.FavoriteLocationEntity

@Dao
interface FavoriteDao {
    @Insert
    suspend fun insert(favorite: FavoriteLocationEntity)

    @Query("SELECT * FROM favorites")
    suspend fun getAll(): List<FavoriteLocationEntity>

    @Delete
    suspend fun delete(favorite: FavoriteLocationEntity)

    @Query("DELETE FROM favorites")
    suspend fun clearAll()
}
