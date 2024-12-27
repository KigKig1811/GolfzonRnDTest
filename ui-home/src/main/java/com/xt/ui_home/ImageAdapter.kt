package com.xt.ui_home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.xt.core_domain.model.ImageModel
import com.xt.resource.databinding.ItemImageBinding
import com.xt.resource.databinding.ItemImageDetailBinding
import com.xt.share.dpToPx

class ImageAdapter(
    private val onLongClick: (ImageModel) -> Unit = {},
    private val onFavClick: (ImageModel) -> Unit = {}
) : PagingDataAdapter<ImageModel, ImageViewHolder>(ImageModel.DiffCallback) {

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val item = getItem(position) ?: return
        holder.bind(item)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ImageViewHolder {
        val binding = ItemImageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ImageViewHolder(binding, onLongClick = onLongClick, onFavClick = onFavClick)
    }

}

class ImageDetailViewHolder(
    private val binding: ItemImageDetailBinding,
    private val onLongClick: (ImageModel) -> Unit = {},
    private val onFavClick: (ImageModel) -> Unit = {}
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ImageModel) {
        val context = binding.root.context

        binding.tvImageId.text = item.id
        binding.cbFav.isChecked = item.isFavorited

        val breed = item.breeds.firstOrNull()
        binding.tvBreed.text = breed?.name ?: ""

        Glide.with(context)
            .load(item.url)
            .placeholder(com.xt.resource.R.drawable.img_placeholder)
            .error(com.xt.resource.R.drawable.img_placeholder)
            .apply(
                RequestOptions()
                    .transform(
                        CenterCrop(),
                        RoundedCorners(context.dpToPx(24))
                    )
            )
            .into(binding.ivImage)

        binding.root.setOnClickListener {
            onFavClick.invoke(item)
        }

        binding.root.setOnLongClickListener {
            onLongClick.invoke(item)
            true
        }
    }

}

class ImageViewHolder(
    private val binding: ItemImageBinding,
    private val onLongClick: (ImageModel) -> Unit = {},
    private val onFavClick: (ImageModel) -> Unit = {}
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ImageModel) {
        binding.tvImageId.text = item.id
        val context = binding.root.context

        binding.cbFav.isChecked = item.isFavorited

        Glide.with(context)
            .load(item.url)
            .placeholder(com.xt.resource.R.drawable.img_placeholder)
            .error(com.xt.resource.R.drawable.img_placeholder)
            .apply(
                RequestOptions()
                    .transform(
                        CenterCrop(),
                        RoundedCorners(context.dpToPx(24))
                    )
            )
            .into(binding.ivImage)

        binding.root.setOnClickListener {
            onFavClick.invoke(item)
        }

        binding.root.setOnLongClickListener {
            onLongClick.invoke(item)
            true
        }
    }
}

