package com.br.lealapps.domain.usecase

interface DeleteTreinoUseCase {
    suspend operator fun invoke(treinoName: String)
}
