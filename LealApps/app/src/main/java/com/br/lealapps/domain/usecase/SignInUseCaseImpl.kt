package com.br.lealapps.domain.usecase

import com.br.lealapps.data.repository.AuthRepository
import com.br.lealapps.domain.model.RepositoryResult
import com.google.firebase.auth.FirebaseUser

class SignInUseCaseImpl(private val authRepository: AuthRepository) : SignInUseCase {
    override suspend operator fun invoke(email: String, password: String): RepositoryResult<FirebaseUser> {
        return try {
            val result = authRepository.signIn(email, password)

            when (result) {
                is RepositoryResult.Success -> RepositoryResult.Success(result.data)
                is RepositoryResult.Error -> RepositoryResult.Error(result.exception)
            }
        } catch (e: Exception) {
            RepositoryResult.Error(e)
        }
    }
}

