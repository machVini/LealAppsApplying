package com.br.lealapps.domain.usecase

import com.br.lealapps.data.source.model.Exercicio
import com.br.lealapps.domain.model.RepositoryResult

interface GetExerciciosUseCase {
    suspend operator fun invoke(): RepositoryResult<List<Exercicio>>
}