package com.xt.ui_home.home

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.xt.core_base.view.BaseViewModel
import com.xt.core_domain.model.ImageModel
import com.xt.core_domain.usecase.FetchImagesUseCase
import com.xt.core_domain.usecase.HandleImageLocalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val fetchImagesUseCase: FetchImagesUseCase,
    private val imageLocalUseCase: HandleImageLocalUseCase
) : BaseViewModel() {

    private val _images = MutableStateFlow<PagingData<ImageModel>>(PagingData.empty())
    val images: StateFlow<PagingData<ImageModel>> = _images

    init {
        fetchImages()
    }

    private fun fetchImages() {
        viewModelScope.launch {
            fetchImagesUseCase.invoke()
                .cachedIn(viewModelScope)
                .combine(imageLocalUseCase.getImages()) { pagingData, favImages ->
                    pagingData.map {
                        it.copy(isFavorited = it.id in favImages.map { image -> image.id })
                    }
                }
                .collectLatest { pagingData ->
                    _images.value = pagingData
                }
        }
    }

    fun updateImage(image: ImageModel) {
        viewModelScope.launch {
            val updatedList = _images.value.map {
                if (it.id == image.id) it.copy(isFavorited = !it.isFavorited) else it
            }
            _images.value = updatedList
        }
    }
}

