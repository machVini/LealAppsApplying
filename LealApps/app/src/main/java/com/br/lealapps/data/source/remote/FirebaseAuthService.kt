package com.br.lealapps.data.source.remote

import android.content.ContentValues.TAG
import android.util.Log
import com.br.lealapps.data.repository.AuthRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import kotlinx.coroutines.tasks.await

class FirebaseAuthService : AuthRepository {

    private val firebaseAuth = Firebase.auth

    override suspend fun signIn(
        email: String,
        password: String,
    ): FirebaseUser? {
        try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            return result.user
        } catch (e: Exception) {
            // Trate a exceção conforme necessário
            return null
        }
    }

    override suspend fun signOut() {
        firebaseAuth.signOut()
    }

    override suspend fun createUser(email: String, password: String): FirebaseUser? {
        return try {
            val result: AuthResult =
                firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            Log.d(TAG, "createUserWithEmail:success:")
            result.user
        } catch (e: Exception) {
            Log.w(TAG, "createUserWithEmail:failure: ${e.message}", e)
            null
        }
    }
}
