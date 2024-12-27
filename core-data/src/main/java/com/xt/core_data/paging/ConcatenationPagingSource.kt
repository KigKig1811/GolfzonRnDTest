package com.xt.core_data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.xt.core_domain.model.ImageModel

class ConcatenationPagingSource(
    private val initialData: List<ImageModel>,
    private val pagingSource: PagingSource<Int, ImageModel>
) : PagingSource<Int, ImageModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImageModel> {
        return when {
            params.key == null -> {
                LoadResult.Page(
                    data = initialData,
                    prevKey = null,
                    nextKey = 2
                )
            }
            else -> {
                pagingSource.load(params)
            }
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ImageModel>): Int? {
        return state.anchorPosition
    }
}
