package com.br.lealapps.domain.usecase

import com.br.lealapps.data.repository.FitnessRepository
import com.br.lealapps.domain.model.Exercicio
import com.br.lealapps.data.source.model.result.RepositoryResult
import javax.inject.Inject

class AddExercicioUseCaseImpl @Inject constructor(
    private val repository: FitnessRepository
) : AddExercicioUseCase {
    override suspend fun invoke(exercicio: Exercicio): RepositoryResult<Unit> {
        return repository.addExercicio(exercicio)
    }
}