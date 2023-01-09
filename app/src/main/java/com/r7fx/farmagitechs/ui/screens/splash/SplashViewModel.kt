package com.r7fx.farmagitechs.ui.screens.splash

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.r7fx.farmagitechs.domain.usecase.GetPatientListUseCase
import com.r7fx.farmagitechs.domain.usecase.LoginUseCase
import com.r7fx.farmagitechs.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    val loginUseCase: LoginUseCase,
    val getPatientListUseCase: GetPatientListUseCase
) : BaseViewModel() {
    private val _redirect = MutableLiveData<Unit>()
    val redirect: LiveData<Unit> = _redirect

    init {
        viewModelScope.launch {
            delay(3000)
            _redirect.postValue(Unit)
        }
    }
}