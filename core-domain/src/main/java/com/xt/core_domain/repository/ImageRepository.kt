package com.xt.core_domain.repository

import androidx.paging.PagingData
import com.xt.core_domain.model.ImageModel
import kotlinx.coroutines.flow.Flow

interface ImageRepository {
    suspend fun fetchImages(startPage: Int = 0): Flow<PagingData<ImageModel>>

    /** local **/
    suspend fun insertImage(image: ImageModel): Flow<Boolean>
    suspend fun getImages(): Flow<List<ImageModel>>
    suspend fun deleteImage(image: ImageModel): Flow<Boolean>
}
