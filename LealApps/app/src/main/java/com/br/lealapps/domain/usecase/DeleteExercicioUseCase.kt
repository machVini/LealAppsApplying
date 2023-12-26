package com.br.lealapps.domain.usecase

interface DeleteExercicioUseCase {
    suspend operator fun invoke(exercicioName: String)
}