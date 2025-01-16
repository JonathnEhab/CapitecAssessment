package com.example.capitecassessment.presenation.ui.auth.viewmodel.mvi

sealed class NewAccountState {
    object Idle : NewAccountState()
    object Loading : NewAccountState()
    object Success : NewAccountState()
    data class Error(val message: String) : NewAccountState()
}