package com.br.lealapps.data.repository

import com.br.lealapps.data.source.model.result.RepositoryResult
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    suspend fun signIn(email: String, password: String): RepositoryResult<FirebaseUser>
    suspend fun signOut()
    suspend fun createUser(email: String, password: String): RepositoryResult<FirebaseUser>
}