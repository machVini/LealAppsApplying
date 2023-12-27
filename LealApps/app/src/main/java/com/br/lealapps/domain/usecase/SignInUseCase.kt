package com.br.lealapps.domain.usecase

import com.br.lealapps.data.source.model.result.RepositoryResult
import com.google.firebase.auth.FirebaseUser

interface SignInUseCase {
    suspend operator fun invoke(email: String, password: String): RepositoryResult<FirebaseUser>
}