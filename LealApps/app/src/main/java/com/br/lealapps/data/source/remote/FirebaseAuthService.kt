package com.br.lealapps.data.source.remote

import com.br.lealapps.data.repository.AuthRepository
import com.br.lealapps.domain.model.AuthError
import com.br.lealapps.domain.model.RepositoryResult
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import kotlinx.coroutines.tasks.await

class FirebaseAuthService : AuthRepository {

    private val firebaseAuth = Firebase.auth

    override suspend fun signIn(email: String, password: String): RepositoryResult<FirebaseUser> {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            RepositoryResult.Success(result.user!!)
        } catch (e: FirebaseAuthException) {
            RepositoryResult.Error(AuthError.InvalidCredentials)
        } catch (e: Exception) {
            RepositoryResult.Error(AuthError.OtherError)
        }
    }


    override suspend fun signOut() {
        firebaseAuth.signOut()
    }

    override suspend fun createUser(email: String, password: String): RepositoryResult<FirebaseUser> {
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            RepositoryResult.Success(result.user!!)
        } catch (e: Exception) {
            RepositoryResult.Error(e)
        }
    }
}
