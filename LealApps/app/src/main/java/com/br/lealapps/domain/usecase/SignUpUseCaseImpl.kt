package com.br.lealapps.domain.usecase

import com.br.lealapps.data.repository.AuthRepository
import com.br.lealapps.data.source.model.result.RepositoryResult
import com.google.firebase.auth.FirebaseUser

class SignUpUseCaseImpl (
    private val repository: AuthRepository
): SignUpUseCase {
    override suspend operator fun invoke(email: String, password: String): RepositoryResult<FirebaseUser> {
        return try {
            when (val result = repository.createUser(email, password)) {
                is RepositoryResult.Success -> RepositoryResult.Success(result.data)
                is RepositoryResult.Error -> RepositoryResult.Error(result.exception)
            }
        } catch (e: Exception) {
            RepositoryResult.Error(e)
        }
    }
}