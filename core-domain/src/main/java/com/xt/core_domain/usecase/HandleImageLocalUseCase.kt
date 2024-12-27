package com.xt.core_domain.usecase

import com.xt.core_domain.model.ImageModel
import com.xt.core_domain.repository.ImageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HandleImageLocalUseCase
@Inject
constructor(
    private val imageRepository: ImageRepository
) {
    suspend fun insertImage(image: ImageModel) =
        imageRepository.insertImage(image.copy(isFavorited = true)).flowOn(Dispatchers.IO)

    suspend fun deleteImage(image: ImageModel) =
        imageRepository.deleteImage(image).flowOn(Dispatchers.IO)

    suspend fun getImages() = imageRepository.getImages().flowOn(Dispatchers.IO)
}
