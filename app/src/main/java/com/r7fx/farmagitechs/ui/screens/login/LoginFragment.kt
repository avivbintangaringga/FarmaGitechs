package com.r7fx.farmagitechs.ui.screens.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.r7fx.farmagitechs.R
import com.r7fx.farmagitechs.databinding.FragmentLoginBinding
import com.r7fx.farmagitechs.databinding.FragmentSplashBinding
import com.r7fx.farmagitechs.ui.base.BaseFragment
import com.r7fx.farmagitechs.ui.screens.splash.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment(R.layout.fragment_login) {
    private val binding: FragmentLoginBinding by viewBinding()
    private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            btnSignIn.setOnClickListener {
                viewModel.login(
                    edtUsername.text.toString(),
                    edtPassword.text.toString()
                )
            }

            with(viewModel) {

                if (checkLogin()) {
                    findNavController().navigate(
                        LoginFragmentDirections.actionLoginFragmentToPatientsFragment()
                    )
                }

                onLoading.onResult {
                    loading.root.isVisible = it
                }
                onLoginResult.onResult {
                    txtErrorMessage.isVisible = !it

                    if (it) {
                        findNavController().navigate(
                            LoginFragmentDirections.actionLoginFragmentToPatientsFragment()
                        )
                    }
                }
                errorMessage.onResult {
                    txtErrorMessage.text = it
                }
            }
        }


    }
}