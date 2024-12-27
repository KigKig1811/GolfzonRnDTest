package com.xt.core_domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BreedModel(
    val weight: DimensionModel = DimensionModel(),
    val height: DimensionModel = DimensionModel(),
    val id: Int = 0,
    val name: String = "",
    val bredFor: String = "",
    val breGroup: String = "",
    val lifeSpan: String = "",
    val temperament: String = "",
    val referenceImageId: String = "",
) : Parcelable
