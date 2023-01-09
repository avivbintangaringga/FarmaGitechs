package com.r7fx.farmagitechs.ui.screens.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.r7fx.farmagitechs.domain.model.Document
import com.r7fx.farmagitechs.domain.model.Patient
import com.r7fx.farmagitechs.domain.usecase.DocumentViewUseCase
import com.r7fx.farmagitechs.domain.usecase.GetDocumentListUseCase
import com.r7fx.farmagitechs.domain.usecase.GetPatientDetailsUseCase
import com.r7fx.farmagitechs.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    val getPatientDetailsUseCase: GetPatientDetailsUseCase,
    val getDocumentListUseCase: GetDocumentListUseCase,
    val documentViewUseCase: DocumentViewUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel() {
    private val patientId = savedStateHandle.get<String>("patientId") ?: ""

    private val _onLoading = MutableLiveData(false)
    val onLoading: LiveData<Boolean> = _onLoading

    private val _onFetchDetailsResult = MutableLiveData<Patient>()
    val onFetchDetailsResult: LiveData<Patient> = _onFetchDetailsResult

    private val _onFetchDocumentsResult = MutableLiveData<List<Document>>()
    val onFetchDocumentsResult: LiveData<List<Document>> = _onFetchDocumentsResult

    private val _onViewDocumentResult = MutableLiveData<Pair<Document, String>>()
    val onViewDocumentResult: LiveData<Pair<Document, String>> = _onViewDocumentResult

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    private val data = mutableListOf<Document>()

    private fun fetchDocuments() {
        runUseCase(
            onLoading = {
                _onLoading.postValue(it)
            },
            onComplete = {
                data.addAll(it)
                _onFetchDocumentsResult.postValue(it)
            },
            onError = {
                _errorMessage.postValue(it)
            }
        ) {
            getDocumentListUseCase(GetDocumentListUseCase.Params(patientId))
        }
    }

    private fun fetch() {
        runUseCase(
            onLoading = {
                _onLoading.postValue(it)
            },
            onComplete = {
                _onFetchDetailsResult.postValue(it)
                fetchDocuments()
            },
            onError = {
                _errorMessage.postValue(it)
            }
        ) {
            getPatientDetailsUseCase(GetPatientDetailsUseCase.Params(patientId))
        }
    }

    fun openDocument(document: Document) {
        runUseCase(
            onLoading = {
                _onLoading.postValue(it)
            },
            onComplete = {
                _onViewDocumentResult.postValue(Pair(document, it))
                fetchDocuments()
            },
            onError = {
                _errorMessage.postValue(it)
            }
        ) {
            documentViewUseCase(DocumentViewUseCase.Params(document.id))
        }
    }

    fun search(key: String) {
        _onFetchDocumentsResult.postValue(data.filter {
            it.title.lowercase().contains(key.lowercase())
        })
    }

    init {
        fetch()
    }
}