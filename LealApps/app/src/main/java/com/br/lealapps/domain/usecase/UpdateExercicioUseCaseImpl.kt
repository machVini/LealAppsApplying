package com.br.lealapps.domain.usecase

import com.br.lealapps.data.repository.FitnessRepository
import com.br.lealapps.domain.model.Exercicio
import com.br.lealapps.data.source.model.result.RepositoryResult
import javax.inject.Inject

class UpdateExercicioUseCaseImpl @Inject constructor(
    private val repository: FitnessRepository
) : UpdateExercicioUseCase {
    override suspend operator fun invoke(exercicio: Exercicio): RepositoryResult<Unit> {
        return repository.updateExercicio(exercicio)
    }

}
