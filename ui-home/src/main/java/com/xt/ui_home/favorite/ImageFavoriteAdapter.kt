package com.xt.ui_home.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.xt.core_domain.model.ImageModel
import com.xt.core_domain.model.ImageModel.Companion.DiffCallback
import com.xt.core_domain.model.ImageModel.Companion.IMAGE_DETAIL
import com.xt.core_domain.model.ImageModel.Companion.IMAGE_LIST
import com.xt.resource.databinding.ItemImageBinding
import com.xt.resource.databinding.ItemImageDetailBinding
import com.xt.ui_home.ImageDetailViewHolder
import com.xt.ui_home.ImageViewHolder

class ImageFavoriteAdapter(
    private val onItemClick: (ImageModel) -> Unit = {},
    private val onFavClick: (ImageModel) -> Unit = {}
) : ListAdapter<ImageModel, RecyclerView.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            IMAGE_LIST -> {
                val binding = ItemImageBinding.inflate(inflater, parent, false)
                ImageViewHolder(binding, onLongClick = onItemClick, onFavClick = onFavClick)
            }
            IMAGE_DETAIL -> {
                val binding = ItemImageDetailBinding.inflate(inflater, parent, false)
                ImageDetailViewHolder(binding, onLongClick = onItemClick, onFavClick = onFavClick)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            when (holder) {
                is ImageViewHolder -> holder.bind(item)
                is ImageDetailViewHolder -> holder.bind(item)
                else -> throw IllegalArgumentException("Invalid ViewHolder type")
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).viewType
    }
}
