package com.br.lealapps.data.repository

interface AuthRepository {
    suspend fun signIn(email: String, password: String, callback: AuthCallback)
    suspend fun signOut()
    suspend fun createUser(email: String, password: String, callback: AuthCallback)
}