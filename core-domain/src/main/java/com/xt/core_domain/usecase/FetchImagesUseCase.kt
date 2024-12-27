package com.xt.core_domain.usecase

import com.xt.core_domain.model.ImageModel
import com.xt.core_domain.repository.ImageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FetchImagesUseCase
@Inject
constructor(
    private val imageRepository: ImageRepository,
) {
    suspend operator fun invoke(startPage: Int = 0)  = withContext(Dispatchers.IO) {
        val response = imageRepository.fetchImages(startPage)
        response
    }

    suspend fun combineImages(startPage: Int = 0,images: List<ImageModel>)= withContext(Dispatchers.IO) {
        val response = imageRepository.fetchImages(startPage,images)
        response
    }
}
