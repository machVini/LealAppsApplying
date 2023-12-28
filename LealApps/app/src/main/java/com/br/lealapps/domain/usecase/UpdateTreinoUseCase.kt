package com.br.lealapps.domain.usecase

import com.br.lealapps.domain.model.Treino
import com.br.lealapps.data.source.model.result.RepositoryResult

interface UpdateTreinoUseCase {
    suspend operator fun invoke(treinoAntigoName: String, treinoNovo: Treino): RepositoryResult<Unit>
}
