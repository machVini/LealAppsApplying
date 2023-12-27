package com.br.lealapps.domain.usecase

import com.br.lealapps.data.repository.FitnessRepository
import com.br.lealapps.data.source.model.result.RepositoryResult
import com.br.lealapps.domain.model.Exercicio

class AddExercicioUseCaseImpl (
    private val repository: FitnessRepository
) : AddExercicioUseCase {
    override suspend fun invoke(exercicio: Exercicio): RepositoryResult<Unit> {
        return repository.addExercicio(exercicio)
    }
}