package com.xt.core_data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadResult.Page
import androidx.paging.PagingState
import com.xt.core_data.mapper.toImageModel
import com.xt.core_data.remote.ImageService
import com.xt.core_domain.model.ImageModel

class ImagePagingSource(
    private val startPage: Int = 0,
    private val imageService: ImageService,
) : PagingSource<Int, ImageModel>() {

    override fun getRefreshKey(state: PagingState<Int, ImageModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImageModel> {
        return try {
            val currentPage = params.key ?: startPage
            val response = imageService.fetchImages(page = currentPage, size = params.loadSize)
            if (response.isSuccessful) {
                val images = response.body()?.map { it.toImageModel() }.orEmpty()
                Page(
                    data = images,
                    prevKey = if (currentPage == 0) null else currentPage - 1,
                    nextKey = if (images.isEmpty()) null else currentPage + 1
                )
            } else {
                LoadResult.Error(Exception("Failed to fetch images"))
            }

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}
