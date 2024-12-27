package com.xt.core_domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DimensionModel(
    val imperial: String = "",
    val metric: String = ""
) : Parcelable
