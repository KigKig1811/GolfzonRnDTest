package com.xt.ui_home

import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.xt.resource.databinding.ItemLoadStateFooterBinding

class LoadStateViewHolder(
    private val binding: ItemLoadStateFooterBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.retryButton.setOnClickListener { retry() }
    }

    fun bind(loadState: LoadState) {
        val isLoading = loadState is LoadState.Loading
        val isError = loadState is LoadState.Error

        binding.progressBar.isVisible = isLoading
        binding.retryButton.isVisible = isError
    }

}
