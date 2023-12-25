package com.br.lealapps.domain.usecase

import com.br.lealapps.data.repository.DatabaseRepository
import com.br.lealapps.data.source.model.Exercicio
import com.br.lealapps.domain.model.RepositoryResult

class AddExercicioUseCaseImpl(private val repository: DatabaseRepository) : AddExercicioUseCase {
    override suspend fun invoke(exercicio: Exercicio): RepositoryResult<Unit> {
        return repository.addExercicio(exercicio)
    }
}