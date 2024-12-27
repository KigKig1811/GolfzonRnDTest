package com.xt.core_data.dto

import com.google.gson.annotations.SerializedName

data class BreedDto(
    val weight: DimensionDto? = null,
    val height: DimensionDto? = null,
    val id: Int? = null,
    val name: String? = null,
    @SerializedName("bred_for")
    val bredFor: String? = null,
    @SerializedName("breed_group")
    val breGroup: String? = null,
    @SerializedName("life_span")
    val lifeSpan: String? = null,
    val temperament: String? = null,
    @SerializedName("reference_image_id")
    val referenceImageId: String? = null,
)
