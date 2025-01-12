package com.xt.core_data.room.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.xt.core_data.room.enity.ImageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ImageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertImage(entity: ImageEntity)

    @Query("SELECT * FROM TABLE_IMAGE")
    fun getImages(): Flow<List<ImageEntity>>

    @Query("DELETE FROM TABLE_IMAGE WHERE id = :id")
    fun deleteImage(id: String)

    @Query("SELECT * FROM TABLE_IMAGE ORDER BY id ASC")
    fun fetchImages(): PagingSource<Int, ImageEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(images: List<ImageEntity>)

    @Query("DELETE FROM TABLE_IMAGE")
    suspend fun clearImages()
}
