package com.r7fx.farmagitechs.ui.screens.patients

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.r7fx.farmagitechs.domain.model.Patient
import com.r7fx.farmagitechs.domain.usecase.GetPatientListUseCase
import com.r7fx.farmagitechs.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PatientsViewModel @Inject constructor(
    val getPatientListUseCase: GetPatientListUseCase
) : BaseViewModel() {
    private val _onLoading = MutableLiveData(false)
    val onLoading: LiveData<Boolean> = _onLoading

    private val _onFetchResult = MutableLiveData<List<Patient>>()
    val onFetchResult: LiveData<List<Patient>> = _onFetchResult

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    private val data = mutableListOf<Patient>()
    private var page: Int = 1
    private var key: String = ""

    var isLast: Boolean = false
        private set

    init {
        fetch()
    }

    private fun fetch() {
        runUseCase(
            onLoading = {
                _onLoading.postValue(it)
            },
            onComplete = {
                data.addAll(it)
                isLast = it.isEmpty()
                _onFetchResult.postValue(data.toList())
            },
            onError = {
                _errorMessage.postValue(it)
            }
        ) {
            getPatientListUseCase(GetPatientListUseCase.Params(key, page))
        }
    }

    fun search(key: String) {
        page = 1
        isLast = false
        this.key = key
        data.clear()
        fetch()
    }

    fun fetchNext() {
        page += 1
        fetch()
    }
}