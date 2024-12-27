package com.xt.core_data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.xt.core_data.mapper.toImageEntity
import com.xt.core_data.mapper.toImageModel
import com.xt.core_data.paging.ConcatenationPagingSource
import com.xt.core_data.paging.ImagePagingSource
import com.xt.core_data.remote.ImageService
import com.xt.core_data.room.dao.ImageDao
import com.xt.core_domain.model.ImageModel
import com.xt.core_domain.repository.ImageRepository
import com.xt.share.LIMIT_IMAGE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ImageRepositoryImpl
@Inject
constructor(
    private val imageService: ImageService,
    private val imageDao: ImageDao
) : ImageRepository {

    override suspend fun fetchImages(startPage: Int): Flow<PagingData<ImageModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = LIMIT_IMAGE,
                enablePlaceholders = false,
                initialLoadSize = LIMIT_IMAGE
            ),
            pagingSourceFactory = { ImagePagingSource(startPage, imageService) },
            initialKey = startPage
        ).flow
    }

    override suspend fun fetchImages(startPage: Int,initialData: List<ImageModel>): Flow<PagingData<ImageModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = LIMIT_IMAGE,
                enablePlaceholders = false,
                initialLoadSize = LIMIT_IMAGE
            ),
            pagingSourceFactory = { ConcatenationPagingSource(initialData, ImagePagingSource(startPage,imageService)) },
            initialKey = startPage
        ).flow
    }

    override suspend fun insertImage(image: ImageModel) = flow {
        val entity = image.toImageEntity()
        imageDao.insertImage(entity = entity)
        emit(true)
    }

    override suspend fun getImages() = imageDao.getImages().map {
        it.map { entity ->
            entity.toImageModel()
        }
    }

    override suspend fun deleteImage(image: ImageModel) = flow {
        imageDao.deleteImage(image.id)
        emit(true)
    }
}
