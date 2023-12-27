package com.br.lealapps.domain.usecase

import com.br.lealapps.data.repository.FitnessRepository
import com.br.lealapps.data.source.model.result.RepositoryResult
import com.br.lealapps.domain.model.Treino

class AddTreinoUseCaseImpl (
    private val repository: FitnessRepository
) : AddTreinoUseCase {
    override suspend fun invoke(treino: Treino): RepositoryResult<Unit> {
        return repository.addTreino(treino)
    }
}