package com.xt.core_data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.xt.core_data.mapper.toImageEntity
import com.xt.core_data.mapper.toImageModel
import com.xt.core_data.paging.ConcatenationPagingSource
import com.xt.core_data.paging.ImagePagingSource
import com.xt.core_data.paging.ImageRemoteMediator
import com.xt.core_data.paging.ImageRemoteMediator1
import com.xt.core_data.remote.ImageService
import com.xt.core_data.room.dao.ImageDao
import com.xt.core_data.room.dao.RemoteKeyDao
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
    private val imageDao: ImageDao,
    private val remoteKeyDao: RemoteKeyDao,
) : ImageRepository {

    @OptIn(ExperimentalPagingApi::class)
    override suspend fun fetchImages(startPage: Int) = Pager(
        config = PagingConfig(pageSize = LIMIT_IMAGE),
        remoteMediator = ImageRemoteMediator1(
            imageDao = imageDao,
            remoteKeyDao = remoteKeyDao,
            imageService = imageService
        ),
        initialKey = 0,
        pagingSourceFactory = {
            imageDao.fetchImages()
        }
    ).flow.map { pagingData ->
        pagingData.map { it.toImageModel() }
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
