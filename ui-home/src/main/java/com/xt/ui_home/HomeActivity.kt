package com.xt.ui_home

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.navigation.findNavController
import com.xt.core_base.extension.toImageArgument
import com.xt.core_base.view.BaseActivity
import com.xt.core_domain.model.ImageModel
import com.xt.share.LIMIT_IMAGE
import com.xt.ui_home.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity: BaseActivity<ActivityHomeBinding>(ActivityHomeBinding::inflate) {

    override val viewModel: HomeActivityViewModel by viewModels()

    private val navController by lazy { findNavController(R.id.fragmentContainerView) }

    private val startActivityForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { _ -> }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        initBottomNavigation()
    }

    fun navigationToDetail(image: ImageModel,images: List<ImageModel>) {
        val intent = Intent(this, DetailActivity::class.java)
        val bundle = image.toImageArgument(images)
        intent.putExtras(bundle)
        startActivityForResult.launch(intent)
    }

    private fun initBottomNavigation() {
        binding.bottomNavView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navItemHome -> {
                    navController.navigate(R.id.homeFragment)
                    true
                }

                R.id.navItemFavorite -> {
                    navController.navigate(R.id.favoriteFragment)
                    true
                }

                else -> true
            }
        }
    }
}
