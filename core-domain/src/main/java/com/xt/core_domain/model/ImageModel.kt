package com.xt.core_domain.model

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImageModel(
    val id: String = "",
    val url: String = "",
    val width: Int = 0,
    val height: Int = 0,
    var isFavorited: Boolean = false,
    val breeds: List<BreedModel> = emptyList(),
    val viewType: Int = IMAGE_LIST
) : Parcelable {
    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<ImageModel>() {
            override fun areItemsTheSame(oldItem: ImageModel, newItem: ImageModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ImageModel, newItem: ImageModel): Boolean {
                return oldItem == newItem
            }
        }

        const val IMAGE_LIST = 0
        const val IMAGE_DETAIL = 1
    }
}

@Parcelize
data class UIModel(
    val currentPage: Int = 0,
    val selectedImage: ImageModel = ImageModel(),
    val currentImages: List<ImageModel> = emptyList()
) : Parcelable
