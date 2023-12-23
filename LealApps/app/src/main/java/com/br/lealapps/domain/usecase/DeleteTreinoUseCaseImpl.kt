package com.br.lealapps.domain.usecase

import com.br.lealapps.data.repository.DatabaseRepository

class DeleteTreinoUseCaseImpl(private val repository: DatabaseRepository) : DeleteTreinoUseCase {
    override suspend operator fun invoke(treinoId: Int) {
        repository.deleteTreino(treinoId)
    }

}