package com.br.lealapps.domain.usecase

import com.br.lealapps.data.repository.FitnessRepository
import com.br.lealapps.domain.model.Treino
import com.br.lealapps.data.source.model.result.RepositoryResult
import javax.inject.Inject

class GetTreinosUseCaseImpl @Inject constructor(
    private val repository: FitnessRepository
) : GetTreinosUseCase {
    override suspend fun invoke(): RepositoryResult<List<Treino>> {
        return repository.getTreinos()
    }

}
