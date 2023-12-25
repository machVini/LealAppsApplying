package com.br.lealapps.domain.usecase

import com.br.lealapps.data.repository.DatabaseRepository
import com.br.lealapps.data.source.model.Treino
import com.br.lealapps.domain.model.RepositoryResult

class AddTreinoUseCaseImpl(private val repository: DatabaseRepository) : AddTreinoUseCase {
    override suspend fun invoke(treino: Treino): RepositoryResult<Unit> {
        return repository.addTreino(treino)
    }
}