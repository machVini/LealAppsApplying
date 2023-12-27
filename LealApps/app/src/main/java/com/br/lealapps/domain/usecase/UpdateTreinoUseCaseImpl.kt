package com.br.lealapps.domain.usecase

import com.br.lealapps.data.repository.FitnessRepository
import com.br.lealapps.data.source.remote.FitnessDataSource
import com.br.lealapps.domain.model.Treino
import com.br.lealapps.data.source.model.result.RepositoryResult
import javax.inject.Inject

class UpdateTreinoUseCaseImpl @Inject constructor(
    private val repository: FitnessRepository
) : UpdateTreinoUseCase {
    override suspend operator fun invoke(treino: Treino): RepositoryResult<Unit> {
        return repository.updateTreino(treino)
    }

}
