package com.br.lealapps.domain.usecase

import com.br.lealapps.data.repository.FitnessRepository
import javax.inject.Inject

class DeleteTreinoUseCaseImpl @Inject constructor(
    private val repository: FitnessRepository
) : DeleteTreinoUseCase {
    override suspend operator fun invoke(treinoName: String) {
        repository.deleteTreino(treinoName)
    }

}
