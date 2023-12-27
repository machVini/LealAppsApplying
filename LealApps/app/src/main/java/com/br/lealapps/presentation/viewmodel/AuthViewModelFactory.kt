package com.br.lealapps.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.br.lealapps.domain.usecase.SignInUseCase
import com.br.lealapps.domain.usecase.SignUpUseCase

class AuthViewModelFactory(
    private val signUpUseCase: SignUpUseCase,
    private val signInUseCase: SignInUseCase,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AuthViewModel(signUpUseCase, signInUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}