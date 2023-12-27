package com.br.lealapps.domain.usecase

import com.br.lealapps.data.repository.FitnessRepository
import javax.inject.Inject

class DeleteExercicioUseCaseImpl @Inject constructor(
    private val repository: FitnessRepository
) : DeleteExercicioUseCase {
    override suspend operator fun invoke(exercicioName: String) {
        repository.deleteExercicio(exercicioName)
    }

}
