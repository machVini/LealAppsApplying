package com.br.lealapps.domain.usecase

import com.br.lealapps.data.repository.FitnessRepository

class DeleteExercicioUseCaseImpl (
    private val repository: FitnessRepository
) : DeleteExercicioUseCase {
    override suspend operator fun invoke(exercicioName: String) {
        repository.deleteExercicio(exercicioName)
    }

}
