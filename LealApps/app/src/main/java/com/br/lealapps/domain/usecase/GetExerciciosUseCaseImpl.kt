package com.br.lealapps.domain.usecase

import com.br.lealapps.data.repository.FitnessRepository
import com.br.lealapps.data.source.model.result.RepositoryResult
import com.br.lealapps.domain.model.Exercicio

class GetExerciciosUseCaseImpl (
    private val repository: FitnessRepository
) : GetExerciciosUseCase {
    override suspend fun invoke(): RepositoryResult<List<Exercicio>> {
        return repository.getExercicios()
    }

}
