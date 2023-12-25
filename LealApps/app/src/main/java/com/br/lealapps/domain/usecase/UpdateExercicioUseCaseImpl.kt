package com.br.lealapps.domain.usecase

import com.br.lealapps.data.repository.DatabaseRepository
import com.br.lealapps.data.source.model.Exercicio
import com.br.lealapps.domain.model.RepositoryResult

class UpdateExercicioUseCaseImpl(private val repository: DatabaseRepository) : UpdateExercicioUseCase {
    override suspend operator fun invoke(exercicio: Exercicio): RepositoryResult<Unit> {
        return repository.updateExercicio(exercicio)
    }

}
