package com.example.capitecassessment.presenation.ui.auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capitecassessment.presenation.ui.auth.repo.LoginRepository
import com.example.capitecassessment.presenation.ui.auth.repo.NewAccountRepository
import com.example.capitecassessment.presenation.ui.auth.viewmodel.mvi.LoginState
import com.example.capitecassessment.presenation.ui.auth.viewmodel.mvi.NewAccountState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateNewAccountViewModel @Inject constructor(private val newAccountRepository: NewAccountRepository) :
    ViewModel() {

    private val _newAccount = MutableStateFlow<NewAccountState>(NewAccountState.Idle)
    val newAccount = _newAccount.asStateFlow()

    fun CreateNewAccount(email: String, password: String) {
        viewModelScope.launch {
            _newAccount.value = NewAccountState.Loading
            try {
                newAccountRepository.CreateNewAccountWithEmail(email, password)
                _newAccount.value = NewAccountState.Success
            } catch (e: Exception) {
                _newAccount.value = NewAccountState.Error(e.message ?: "Unknown error")
            }
        }
    }
}