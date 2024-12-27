package com.xt.ui_home

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
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailViewModel
@Inject constructor(
    private val imageLocalUseCase: HandleImageLocalUseCase
) : BaseViewModel() {

    private val _images = MutableStateFlow<List<ImageModel>>(emptyList())
    val images = _images.asStateFlow()

    var selectImage: ImageModel = ImageModel()
    var currentImages: List<ImageModel> = emptyList()

    var isInitial = true

    fun toggleFavorite(item: ImageModel) {
        viewModelScope.launch {
            if (item.isFavorited) {
                imageLocalUseCase.deleteImage(item).collect {
                    Timber.d("KKK deleteImage : $it")
                }
            } else {
                imageLocalUseCase.insertImage(item).collect {
                    Timber.d("KKK insert : $it")
                }
            }
        }
    }

    fun setInitialImages(images: List<ImageModel>) {
        viewModelScope.launch {
            flowOf(images).combine(imageLocalUseCase.getImages()) { images, favImages ->
                images.map {
                    it.copy(isFavorited = it.id in favImages.map { image -> image.id })
                }
            }.collectLatest {
                _images.value = it
            }
        }
    }
}
