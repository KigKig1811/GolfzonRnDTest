package com.xt.core_base.extension

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.core.os.bundleOf
import com.xt.core_domain.model.ImageModel
import com.xt.core_domain.model.UIModel
import com.xt.share.CURRENT_IMAGES
import com.xt.share.CURRENT_PAGE
import com.xt.share.SELECTED_IMAGE

inline fun <reified T : Parcelable> Bundle.parcelable(key: String): T? = when {
    Build.VERSION.SDK_INT >= 33 -> getParcelable(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelable(key) as? T
}

fun Bundle.getImageArgument(): UIModel? = parcelable(CURRENT_IMAGES)
fun ImageModel.toImageArgument(images: List<ImageModel> = emptyList()) = bundleOf(
    CURRENT_IMAGES to UIModel(
        selectedImage = this,
        currentImages = images
    )
)
