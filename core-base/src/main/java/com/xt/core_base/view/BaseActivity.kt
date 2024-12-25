package com.xt.core_base.view

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB: ViewBinding>(
    private val inflater: (LayoutInflater) -> VB
) : AppCompatActivity() {

    private var _binding: VB? = null
    protected val binding:VB
        get() = _binding!!

    abstract val viewModel: BaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = inflater.invoke(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            onActivityInitialized()
        }
        onActivityCreated(savedInstanceState)
    }

    protected open fun onActivityInitialized() {
        // for override
    }
    protected abstract fun onActivityCreated(savedInstanceState: Bundle?)
}
