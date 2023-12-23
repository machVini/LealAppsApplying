package com.br.lealapps.data.repository

import com.br.lealapps.domain.model.AuthError
import com.google.firebase.auth.FirebaseUser

interface AuthCallback {
    fun onAuthSuccess(user: FirebaseUser)
    fun onAuthFailed(error: AuthError)
}