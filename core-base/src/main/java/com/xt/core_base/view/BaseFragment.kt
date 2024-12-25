package com.xt.core_base.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import timber.log.Timber

abstract class BaseFragment<VB : ViewBinding>(
    private val inflate: (LayoutInflater) -> VB,
) : Fragment() {
    private var _binding: VB? = null
    protected val binding: VB
        get() = _binding!!

    abstract val viewModel: BaseViewModel

    protected open var onBackPressedCallback: OnBackPressedCallback =
        object : OnBackPressedCallback(false) {
            override fun handleOnBackPressed() {
                onFragmentBackPressed()
            }
        }

    open fun onFragmentBackPressed() {
        onBackPressedCallback.isEnabled = false
        activity?.onBackPressedDispatcher?.onBackPressed()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, onBackPressedCallback)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        Timber.d("onCreateView...")
        _binding = inflate.invoke(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            onFragmentInitialized()
        }

        onFragmentCreated(savedInstanceState)
    }

    open fun onFragmentInitialized() {}

    abstract fun onFragmentCreated(savedInstanceState: Bundle?)
}
