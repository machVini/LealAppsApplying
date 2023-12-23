package com.br.lealapps.domain.usecase

import com.br.lealapps.data.repository.DatabaseRepository
import com.br.lealapps.data.source.model.Treino

class AddTreinoUseCaseImpl(private val repository: DatabaseRepository) : AddTreinoUseCase {
    override suspend fun invoke(treino: Treino) {
        repository.addTreino(treino)
    }
}