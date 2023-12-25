package com.br.lealapps.domain.usecase

import com.br.lealapps.data.repository.DatabaseRepository
import com.br.lealapps.data.source.model.Treino
import com.br.lealapps.domain.model.RepositoryResult

class UpdateTreinoUseCaseImpl(private val repository: DatabaseRepository) : UpdateTreinoUseCase {
    override suspend operator fun invoke(treino: Treino): RepositoryResult<Unit> {
        return repository.updateTreino(treino)
    }

}
