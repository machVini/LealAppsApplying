package com.br.lealapps.domain.usecase

import com.br.lealapps.data.source.model.Exercicio

interface UpdateExercicioUseCase {
    suspend operator fun invoke(exercicio: Exercicio)
}
