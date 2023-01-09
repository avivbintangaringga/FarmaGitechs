package com.r7fx.farmagitechs.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope

abstract class BaseViewModel : ViewModel(), UseCaseRunner {
    override val coroutineScope = viewModelScope
}