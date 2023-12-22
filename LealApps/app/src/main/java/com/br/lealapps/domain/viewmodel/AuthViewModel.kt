package com.br.lealapps.domain.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.lealapps.data.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthViewModel(private val authRepository: AuthRepository) : ViewModel() {

    private val _authenticatedUser = MutableLiveData<FirebaseUser?>()
    val authenticatedUser: LiveData<FirebaseUser?> = _authenticatedUser

    private val _loginStatus = MutableLiveData<LoginStatus>()
    val loginStatus: LiveData<LoginStatus> = _loginStatus

    enum class LoginStatus {
        SUCCESS, // Login bem-sucedido
        INVALID_CREDENTIALS, // Credenciais inválidas (por exemplo, senha incorreta)
        ERROR // Outro erro no login
    }

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            val user = withContext(Dispatchers.IO) {
                authRepository.signIn(email, password)
            }
            _authenticatedUser.value = user

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

    fun createUserWithEmailAndPassword(email: String, password: String) {
        viewModelScope.launch {
            val user = withContext(Dispatchers.IO) {
                authRepository.createUser(email, password)
            }
            _authenticatedUser.value = user
        }
    }
}
