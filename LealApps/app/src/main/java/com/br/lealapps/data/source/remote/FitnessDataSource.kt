package com.br.lealapps.data.source.remote

import com.br.lealapps.data.source.model.ExercicioResponse
import com.br.lealapps.data.source.model.TreinoResponse
import com.br.lealapps.data.source.model.result.RepositoryResult
import com.google.firebase.firestore.DocumentReference

interface FitnessDataSource {
    suspend fun addTreino(treino: TreinoResponse): RepositoryResult<Unit>
    suspend fun getTreinos(): RepositoryResult<List<TreinoResponse>>
    suspend fun updateTreino(
        treinoAntigoName: String,
        treinoNovo: TreinoResponse
    ): RepositoryResult<Unit>

    suspend fun deleteTreino(treinoName: String): RepositoryResult<Unit>
    suspend fun addExercicio(exercicio: ExercicioResponse): RepositoryResult<Unit>
    suspend fun getExercicios(): RepositoryResult<List<ExercicioResponse>>
    suspend fun updateExercicio(exercicio: ExercicioResponse): RepositoryResult<Unit>
    suspend fun deleteExercicio(exercicioName: String): RepositoryResult<Unit>
    suspend fun getDocReferenceByName(exercicioName: String): DocumentReference?
    suspend fun getExercicioByDocRef(docRef: DocumentReference): ExercicioResponse?
}