package com.br.lealapps.domain.usecase

import com.br.lealapps.data.source.model.Treino

interface AddTreinoUseCase {
    suspend operator fun invoke(treino: Treino)
}