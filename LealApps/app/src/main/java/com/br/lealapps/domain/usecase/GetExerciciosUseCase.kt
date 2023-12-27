package com.br.lealapps.domain.usecase

import com.br.lealapps.domain.model.Exercicio
import com.br.lealapps.data.source.model.result.RepositoryResult

interface GetExerciciosUseCase {
    suspend operator fun invoke(): RepositoryResult<List<Exercicio>>
}