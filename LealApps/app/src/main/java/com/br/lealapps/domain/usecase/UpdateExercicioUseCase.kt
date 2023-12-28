package com.br.lealapps.domain.usecase

import com.br.lealapps.domain.model.Exercicio
import com.br.lealapps.data.source.model.result.RepositoryResult

interface UpdateExercicioUseCase {
    suspend operator fun invoke(
        exercicioAntigoName: String,
        exercicioNovo: Exercicio
    ): RepositoryResult<Unit>
}
