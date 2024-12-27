package com.xt.ui_home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewpager2.widget.ViewPager2
import com.xt.core_base.extension.getImageArgument
import com.xt.core_base.view.BaseActivity
import com.xt.core_domain.model.ImageModel.Companion.IMAGE_DETAIL
import com.xt.ui_home.databinding.ActivityDetailBinding
import com.xt.ui_home.favorite.ImageFavoriteAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailActivity : BaseActivity<ActivityDetailBinding>(ActivityDetailBinding::inflate) {

    override val viewModel: DetailViewModel by viewModels()

    private val adapter: ImageFavoriteAdapter by lazy {
        ImageFavoriteAdapter(
            onFavClick = {
                viewModel.toggleFavorite(it)
            }
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        val data = intent.extras?.getImageArgument() ?: return

        viewModel.currentImages = data.currentImages
        viewModel.selectImage = data.selectedImage

        binding.vpImage.adapter = adapter
        binding.vpImage.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        val images = data.currentImages.map { it.copy(viewType = IMAGE_DETAIL) }
        viewModel.setInitialImages(images)
        initObservers()
    }

    private fun initObservers() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.images.collect {
                    if (it.isEmpty()) return@collect
                    adapter.submitList(it) {
                        if (viewModel.isInitial) {
                            val position = viewModel.currentImages.indexOf(viewModel.selectImage)
                            if (position == -1) return@submitList
                            binding.vpImage.setCurrentItem(position, false)
                            viewModel.isInitial = false
                        }
                    }
                }
            }
        }
    }

}
