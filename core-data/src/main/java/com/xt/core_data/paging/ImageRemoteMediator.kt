package com.xt.core_data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.xt.core_data.mapper.toImageEntity
import com.xt.core_data.mapper.toImageModel
import com.xt.core_data.remote.ImageService
import com.xt.core_data.room.dao.ImageDao
import com.xt.core_data.room.dao.RemoteKeyDao
import com.xt.core_data.room.enity.RemoteKey
import com.xt.core_domain.model.ImageModel
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class ImageRemoteMediator(
    private val imageDao: ImageDao,
    private val remoteKeyDao: RemoteKeyDao,
    private val imageService: ImageService
) : RemoteMediator<Int, ImageModel>() {

    override suspend fun load(
        loadType: LoadType, state: PagingState<Int, ImageModel>
    ): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> 0
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull() ?: return MediatorResult.Success(
                        endOfPaginationReached = true
                    )
                    val remoteKey = remoteKeyDao.remoteKey(query = "image_list_${lastItem.id}")
                    if (remoteKey.nextKey == null) {
                        return MediatorResult.Success(endOfPaginationReached = true)
                    }

                    remoteKey.nextKey
                }
            }
            val response = imageService.fetchImages(
                page = page,
                size = when (loadType) {
                    LoadType.REFRESH -> state.config.initialLoadSize
                    else -> state.config.pageSize
                }
            ).body()?.map { it.toImageModel() } ?: emptyList()

            if (loadType == LoadType.REFRESH) {
                imageDao.clearImages()
                remoteKeyDao.clearRemoteKeys()
            }

            remoteKeyDao.insert(RemoteKey("image_list_${page}", page + 1))
            imageDao.insertAll(response.map { it.toImageEntity() })

            MediatorResult.Success(endOfPaginationReached = response.isEmpty())
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}