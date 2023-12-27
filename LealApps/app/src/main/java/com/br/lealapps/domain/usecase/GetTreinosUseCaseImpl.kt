package com.br.lealapps.domain.usecase

import com.br.lealapps.data.repository.FitnessRepository
import com.br.lealapps.data.source.model.result.RepositoryResult
import com.br.lealapps.domain.model.Treino

class GetTreinosUseCaseImpl (
    private val repository: FitnessRepository
) : GetTreinosUseCase {
    override suspend fun invoke(): RepositoryResult<List<Treino>> {
        return repository.getTreinos()
    }

}
