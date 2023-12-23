package com.br.lealapps.domain.usecase

import com.br.lealapps.data.repository.DatabaseRepository
import com.br.lealapps.data.source.model.Exercicio

class UpdateExercicioUseCaseImpl(private val repository: DatabaseRepository) : UpdateExercicioUseCase {
    override suspend operator fun invoke(exercicio: Exercicio) {
        repository.updateExercicio(exercicio)
    }

}
