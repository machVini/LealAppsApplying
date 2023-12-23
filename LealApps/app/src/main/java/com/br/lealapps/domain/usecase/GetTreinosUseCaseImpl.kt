package com.br.lealapps.domain.usecase

import com.br.lealapps.data.repository.DatabaseRepository
import com.br.lealapps.data.source.model.Treino
import com.br.lealapps.domain.model.RepositoryResult

class GetTreinosUseCaseImpl(private val repository: DatabaseRepository) : GetTreinosUseCase {
    override suspend fun invoke(): RepositoryResult<List<Treino>> {
        return repository.getTreinos()
    }

}
