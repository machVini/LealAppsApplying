package com.br.lealapps.data.repository

import com.br.lealapps.data.source.model.Exercicio
import com.br.lealapps.data.source.model.Treino
import com.br.lealapps.domain.model.RepositoryResult

interface DatabaseRepository {
    suspend fun addTreino(treino: Treino): RepositoryResult<Unit>
    suspend fun getTreinos(): RepositoryResult<List<Treino>>
    suspend fun updateTreino(treino: Treino): RepositoryResult<Unit>
    suspend fun deleteTreino(treinoName: String): RepositoryResult<Unit>
    suspend fun addExercicio(exercicio: Exercicio): RepositoryResult<Unit>
    suspend fun getExercicios(): RepositoryResult<List<Exercicio>>
    suspend fun updateExercicio(exercicio: Exercicio): RepositoryResult<Unit>
    suspend fun deleteExercicio(exercicioId: Int): RepositoryResult<Unit>
}