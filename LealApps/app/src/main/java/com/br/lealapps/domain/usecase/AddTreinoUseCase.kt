package com.br.lealapps.domain.usecase

import com.br.lealapps.domain.model.Treino
import com.br.lealapps.data.source.model.result.RepositoryResult

interface AddTreinoUseCase {
    suspend operator fun invoke(treino: Treino): RepositoryResult<Unit>
}