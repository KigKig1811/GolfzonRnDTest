package com.xt.ui_home.favorite

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.xt.core_base.view.BaseViewModel
import com.xt.core_domain.model.ImageModel
import com.xt.core_domain.usecase.HandleImageLocalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val imagesUseCase: HandleImageLocalUseCase
) : BaseViewModel() {

    private val _images = MutableStateFlow<List<ImageModel>>(emptyList())
    val images = _images.asStateFlow()

    init {
        fetchImages()
    }

    private fun fetchImages() {
        viewModelScope.launch {
            imagesUseCase.getImages().collectLatest { images ->
                _images.value = images
            }
        }
    }
}
