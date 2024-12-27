package com.xt.core_data.dto

data class ImageDto(
    val id: String? = null,
    val url: String? = null,
    val width: Int? = null,
    val height: Int? = null,
    val breeds: List<BreedDto>? = null,
)
