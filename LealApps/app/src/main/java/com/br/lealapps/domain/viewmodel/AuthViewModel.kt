package com.br.lealapps.domain.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.lealapps.data.repository.AuthCallback
import com.br.lealapps.data.repository.AuthRepository
import com.br.lealapps.domain.model.AuthError
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthViewModel(private val authRepository: AuthRepository) : ViewModel() {

    private val _authenticatedUser = MutableLiveData<FirebaseUser?>()
    val authenticatedUser: LiveData<FirebaseUser?> = _authenticatedUser

    private val _loginStatus = MutableLiveData<LoginStatus>()
    val loginStatus: LiveData<LoginStatus> = _loginStatus

    private val _authError = MutableLiveData<AuthError?>()
    val authError: LiveData<AuthError?> = _authError

    enum class LoginStatus {
        SUCCESS, // Login bem-sucedido
        INVALID_CREDENTIALS, // Credenciais inválidas (por exemplo, senha incorreta)
        ERROR // Outro erro no login
    }

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            authRepository.signIn(email, password, object : AuthCallback {
                override fun onAuthSuccess(user: FirebaseUser) {
                    _authenticatedUser.value = user
                    _authError.value = null  // Limpa o estado de erro em caso de sucesso
                }

                override fun onAuthFailed(error: AuthError) {
                    _authError.value = error
                }
            })
        }
    }

    fun signOut() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                authRepository.signOut()
            }
            _authenticatedUser.value = null
        }
    }

    fun createUser(email: String, password: String, confirmPassword: String) {
        if (password == confirmPassword) {
            viewModelScope.launch {
                authRepository.createUser(email, password, object : AuthCallback {
                    override fun onAuthSuccess(user: FirebaseUser) {
                        _authenticatedUser.value = user
                        _authError.value = null
                    }

                    override fun onAuthFailed(error: AuthError) {
                        _authError.value = error
                    }
                })
            }
        } else {
            _authError.value = AuthError.UnmatchPasswordsError
        }
    }
}
