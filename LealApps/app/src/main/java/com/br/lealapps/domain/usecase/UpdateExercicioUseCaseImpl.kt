package com.br.lealapps.domain.usecase

import com.br.lealapps.data.repository.FitnessRepository
import com.br.lealapps.data.source.model.result.RepositoryResult
import com.br.lealapps.domain.model.Exercicio

class UpdateExercicioUseCaseImpl (
    private val repository: FitnessRepository
) : UpdateExercicioUseCase {
    override suspend operator fun invoke(exercicio: Exercicio): RepositoryResult<Unit> {
        return repository.updateExercicio(exercicio)
    }

}
