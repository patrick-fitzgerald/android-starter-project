package com.example.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.databinding.FragmentHomeBinding
import com.example.ui.base.BaseFragment
import com.example.util.autoCleared
import io.reactivex.rxkotlin.addTo
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class HomeFragment : BaseFragment() {

    private var viewBinding by autoCleared<FragmentHomeBinding>()
    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentHomeBinding.inflate(inflater, container, false)
        viewBinding.viewModel = homeViewModel
        viewBinding.lifecycleOwner = this

        return viewBinding.root
    }

    override fun onStart() {
        super.onStart()
        Timber.d("HomeFragment - onStart")

        // click events
        homeViewModel.contextEventBus.subscribe { contextEvent ->
            context?.let {
                when (contextEvent) {
                    HomeViewModel.ContextEvent.BUTTON_CLICKED -> Unit
                    else -> Unit
                }
            }
        }.addTo(compositeDisposable)
    }
}
