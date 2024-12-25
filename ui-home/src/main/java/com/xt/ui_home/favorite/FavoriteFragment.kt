package com.xt.ui_home.favorite

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.xt.core_base.view.BaseFragment
import com.xt.resource.GridSpacingItemDecoration
import com.xt.ui_home.HomeActivity
import com.xt.ui_home.HomeActivityViewModel
import com.xt.ui_home.databinding.FragmentFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteFragment
@Inject
constructor(

) : BaseFragment<FragmentFavoriteBinding>(FragmentFavoriteBinding::inflate) {

    override val viewModel: FavoriteViewModel by viewModels()
    private val parentVM: HomeActivityViewModel by activityViewModels()

    private val imageAdapter: ImageFavoriteAdapter by lazy {
        ImageFavoriteAdapter(
            onItemClick = {
                (activity as? HomeActivity)?.navigationToDetail(
                    image = it,
                    images = imageAdapter.currentList
                )
            },
            onFavClick = { image ->
                parentVM.toggleFavorite(image)
            }
        )
    }

    override fun onFragmentCreated(savedInstanceState: Bundle?) {
        initUi()
        initObservers()
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.images.collectLatest { images ->
                    imageAdapter.submitList(images)
                }
            }
        }
    }

    private fun initUi() {
        binding.rcvImages.addItemDecoration(GridSpacingItemDecoration(3, 10, true))
        binding.rcvImages.layoutManager = GridLayoutManager(context, 3)
        binding.rcvImages.adapter = imageAdapter
    }
}
