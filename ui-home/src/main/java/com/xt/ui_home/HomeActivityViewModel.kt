package com.xt.ui_home

import androidx.lifecycle.viewModelScope
import com.xt.core_base.view.BaseViewModel
import com.xt.core_domain.model.ImageModel
import com.xt.core_domain.usecase.HandleImageLocalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeActivityViewModel @Inject constructor(
    private val imageLocalUseCase: HandleImageLocalUseCase
) : BaseViewModel() {

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
}
