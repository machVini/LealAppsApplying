package com.br.lealapps.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.lealapps.domain.model.AuthError
import com.br.lealapps.domain.model.RepositoryResult
import com.br.lealapps.domain.usecase.SignInUseCase
import com.br.lealapps.domain.usecase.SignUpUseCase
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch

class AuthViewModel(
    private val signUpUseCase: SignUpUseCase,
    private val signInUseCase: SignInUseCase,
) : ViewModel() {

    private val _authenticatedUser = MutableLiveData<FirebaseUser?>()
    val authenticatedUser: LiveData<FirebaseUser?> = _authenticatedUser

    private val _authenticationState = MutableLiveData(AuthenticationState.NOT_LOGGED)
    val authenticationState: LiveData<AuthenticationState> get() = _authenticationState

    private val _authError = MutableLiveData<AuthError?>()
    val authError: LiveData<AuthError?> = _authError

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            try {
                val result = signInUseCase(email, password)
                when (result) {
                    is RepositoryResult.Success -> {
                        _authenticatedUser.value = result.data
                        _authenticationState.value = AuthenticationState.AUTHENTICATED
                        _authError.value = null
                    }
                    is RepositoryResult.Error -> {
                        _authError.value = AuthError.InvalidCredentials
                    }
                }
            } catch (e: Exception) {
                _authError.value = AuthError.OtherError
            }
        }
    }

    fun createUser(email: String, password: String, confirmPassword: String) {
        if (password == confirmPassword) {
            viewModelScope.launch {
                try {
                    val result = signUpUseCase(email, password)
                    when (result) {
                        is RepositoryResult.Success -> {
                            _authenticatedUser.value = result.data
                            _authenticationState.value = AuthenticationState.AUTHENTICATED
                            _authError.value = null
                        }
                        is RepositoryResult.Error -> {
                            _authError.value = AuthError.CreateUserError
                        }
                    }
                } catch (e: Exception) {
                    _authError.value = AuthError.OtherError
                }
            }
        } else {
            _authError.value = AuthError.UnmatchPasswordsError
        }
    }



    enum class AuthenticationState {
        AUTHENTICATED,
        NOT_LOGGED,
    }
}
