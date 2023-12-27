package com.br.lealapps.domain.mapper

import com.br.lealapps.data.source.model.ExercicioResponse
import com.br.lealapps.data.source.model.TreinoResponse
import com.br.lealapps.domain.model.Exercicio
import com.br.lealapps.domain.model.Treino
import com.google.firebase.firestore.DocumentReference

interface FitnessResponseMapper {
    suspend fun mapTreinoFromResponse(response: TreinoResponse, exercicios: List<Exercicio>): Treino
    suspend fun mapTreinoToResponse(domain: Treino, exercicios: List<DocumentReference>): TreinoResponse
    suspend fun mapExercicioFromResponse(response: ExercicioResponse): Exercicio
    suspend fun mapExercicioToResponse(domain: Exercicio): ExercicioResponse
}