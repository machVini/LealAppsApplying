package com.br.lealapps.domain.usecase

import com.br.lealapps.domain.model.RepositoryResult
import com.google.firebase.auth.FirebaseUser

interface SignUpUseCase {
    suspend operator fun invoke(email: String, password: String): RepositoryResult<FirebaseUser>
}