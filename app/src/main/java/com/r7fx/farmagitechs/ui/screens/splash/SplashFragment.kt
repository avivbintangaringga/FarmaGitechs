package com.r7fx.farmagitechs.ui.screens.splash

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.r7fx.farmagitechs.R
import com.r7fx.farmagitechs.databinding.FragmentDetailsBinding
import com.r7fx.farmagitechs.databinding.FragmentSplashBinding
import com.r7fx.farmagitechs.ui.base.BaseFragment
import com.r7fx.farmagitechs.ui.screens.details.DetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment(R.layout.fragment_splash) {
    private val binding: FragmentSplashBinding by viewBinding()
    private val viewModel: SplashViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewModel) {
            redirect.onResult {
                findNavController().navigate(
                    SplashFragmentDirections.actionSplashFragmentToLoginFragment()
                )
            }
        }
    }
}