package com.br.lealapps.domain.usecase

import com.br.lealapps.data.source.model.Exercicio

interface AddExercicioUseCase {
    suspend operator fun invoke(exercicio: Exercicio)
}