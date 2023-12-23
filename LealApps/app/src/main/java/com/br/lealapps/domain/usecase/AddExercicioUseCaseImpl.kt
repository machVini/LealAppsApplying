package com.br.lealapps.domain.usecase

import com.br.lealapps.data.repository.DatabaseRepository
import com.br.lealapps.data.source.model.Exercicio

class AddExercicioUseCaseImpl(private val repository: DatabaseRepository) : AddExercicioUseCase {
    override suspend fun invoke(exercicio: Exercicio) {
        repository.addExercicio(exercicio)
    }
}