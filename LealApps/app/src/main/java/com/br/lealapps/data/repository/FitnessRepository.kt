package com.br.lealapps.data.repository

import com.br.lealapps.domain.model.Exercicio
import com.br.lealapps.domain.model.Treino
import com.br.lealapps.data.source.model.result.RepositoryResult
import com.google.firebase.firestore.DocumentReference

interface FitnessRepository {
    suspend fun addTreino(treino: Treino): RepositoryResult<Unit>
    suspend fun getTreinos(): RepositoryResult<List<Treino>>
    suspend fun updateTreino(treinoAntigoName: String, treinoNovo: Treino): RepositoryResult<Unit>
    suspend fun deleteTreino(treinoName: String): RepositoryResult<Unit>

    suspend fun addExercicio(exercicio: Exercicio): RepositoryResult<Unit>
    suspend fun getExercicios(): RepositoryResult<List<Exercicio>>
    suspend fun updateExercicio(exercicioAntigoName: String, exercicioNovo: Exercicio): RepositoryResult<Unit>
    suspend fun deleteExercicio(exercicioName: String): RepositoryResult<Unit>
    suspend fun getDocReferenceByName(exercicioName: String): DocumentReference?
    suspend fun getExercicioByDocRef(docRef: DocumentReference): Exercicio?
}