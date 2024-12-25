package com.xt.ui_home.home

import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.xt.core_base.view.BaseFragment
import com.xt.resource.GridSpacingItemDecoration
import com.xt.ui_home.HomeActivity
import com.xt.ui_home.HomeActivityViewModel
import com.xt.ui_home.ImageAdapter
import com.xt.ui_home.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment
@Inject
constructor() : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    override val viewModel: HomeViewModel by viewModels()

    private val parentVM: HomeActivityViewModel by activityViewModels()

    private val imageAdapter: ImageAdapter by lazy {
        ImageAdapter(
            onLongClick = {
                (activity as? HomeActivity)?.navigationToDetail(
                    image = it,
                    images = imageAdapter.snapshot().items
                )
            },
            onFavClick = { image ->
                parentVM.toggleFavorite(image)
            }
        )
    }

    override fun onFragmentCreated(savedInstanceState: Bundle?) {
        initUi()
        initListeners()
        initObservers()
    }

    private fun initListeners() {
        imageAdapter.addLoadStateListener { loadState ->
            binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading

            if (loadState.source.refresh is LoadState.Error) {
                val error = (loadState.source.refresh as LoadState.Error).error
                Toast.makeText(context, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initUi() {
        binding.rcvImages.addItemDecoration(GridSpacingItemDecoration(3, 10, true))
        val footerAdapter = com.xt.ui_home.LoadStateAdapter { imageAdapter.retry() }

        val combinedAdapter = imageAdapter.withLoadStateFooter(footer = footerAdapter)
        val layoutManager = GridLayoutManager(context, 3).apply {
            spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return if (position == combinedAdapter.itemCount - 1) spanCount else 1
                }
            }
        }

        binding.rcvImages.layoutManager = layoutManager
        binding.rcvImages.adapter = combinedAdapter
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.images.collectLatest { pagingData ->
                    imageAdapter.submitData(pagingData)
                }
            }
        }
    }
}
