package com.br.lealapps.domain.usecase

import com.br.lealapps.data.repository.DatabaseRepository
import com.br.lealapps.data.source.model.Exercicio
import com.br.lealapps.domain.model.RepositoryResult

class GetExerciciosUseCaseImpl(private val repository: DatabaseRepository) : GetExerciciosUseCase {
    override suspend fun invoke(): RepositoryResult<List<Exercicio>> {
        return repository.getExercicios()
    }

}
