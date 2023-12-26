package com.br.lealapps.domain.usecase

import com.br.lealapps.data.repository.DatabaseRepository

class DeleteExercicioUseCaseImpl(private val repository: DatabaseRepository) : DeleteExercicioUseCase {
    override suspend operator fun invoke(exercicioName: String) {
        repository.deleteExercicio(exercicioName)
    }

}
