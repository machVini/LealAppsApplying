package com.br.lealapps.domain.usecase

import com.br.lealapps.data.repository.FitnessRepository
import com.br.lealapps.domain.model.Exercicio
import com.br.lealapps.data.source.model.result.RepositoryResult
import javax.inject.Inject

class GetExerciciosUseCaseImpl @Inject constructor(
    private val repository: FitnessRepository
) : GetExerciciosUseCase {
    override suspend fun invoke(): RepositoryResult<List<Exercicio>> {
        return repository.getExercicios()
    }

}
