package com.br.lealapps.domain.usecase

import com.br.lealapps.data.source.model.Treino
import com.br.lealapps.domain.model.RepositoryResult

interface AddTreinoUseCase {
    suspend operator fun invoke(treino: Treino): RepositoryResult<Unit>
}