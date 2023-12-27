package com.br.lealapps.data.repository

import com.br.lealapps.data.source.model.result.RepositoryResult
import com.br.lealapps.data.source.remote.AuthDataSource
import com.google.firebase.auth.FirebaseUser

class FirebaseAuthRepository (
    private val dataSource: AuthDataSource,
) : AuthRepository {
    override suspend fun signIn(email: String, password: String): RepositoryResult<FirebaseUser> {
        return dataSource.signIn(email, password)
    }

    override suspend fun signOut() {
        return dataSource.signOut()
    }

    override suspend fun createUser(
        email: String,
        password: String
    ): RepositoryResult<FirebaseUser> {
        return dataSource.createUser(email, password)
    }
}