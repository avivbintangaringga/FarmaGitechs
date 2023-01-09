package com.r7fx.farmagitechs.ui.screens.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.r7fx.farmagitechs.common.utils.Preferences
import com.r7fx.farmagitechs.domain.usecase.LoginUseCase
import com.r7fx.farmagitechs.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    val loginUseCase: LoginUseCase,
    val prefs: Preferences
) : BaseViewModel() {
    private val _onLoading = MutableLiveData(false)
    val onLoading: LiveData<Boolean> = _onLoading

    private val _onLoginResult = MutableLiveData<Boolean>()
    val onLoginResult: LiveData<Boolean> = _onLoginResult

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun checkLogin() = prefs.token.isNotEmpty()

    fun login(username: String, password: String) {
        runUseCase(
            onLoading = {
                _onLoading.postValue(it)
            },
            onComplete = {
                _onLoginResult.postValue(true)
            },
            onError = {
                _errorMessage.postValue(it)
                _onLoginResult.postValue(false)
            }
        ) {
            loginUseCase(LoginUseCase.Params(username, password))
        }
    }
}