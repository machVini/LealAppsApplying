package com.br.lealapps.data.source.remote

import android.content.ContentValues.TAG
import android.util.Log
import com.br.lealapps.data.repository.DatabaseRepository
import com.br.lealapps.data.source.model.Exercicio
import com.br.lealapps.data.source.model.Treino
import com.br.lealapps.domain.model.RepositoryResult
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FirestoreDatabaseService(private val firestore: FirebaseFirestore) : DatabaseRepository {
    override suspend fun addTreino(treino: Treino): RepositoryResult<Unit> {
        return try {
            Log.d(TAG, "Attempting to add treino: $treino")
            firestore.collection("Treino").add(treino).await()
            Log.d(TAG, "Treino added successfully.")
            RepositoryResult.Success(Unit)
        } catch (e: Exception) {
            Log.e(TAG, "Error adding treino: $treino", e)
            RepositoryResult.Error(e)
        }
    }

    override suspend fun getTreinos(): RepositoryResult<List<Treino>> {
        return try {
            val querySnapshot = firestore.collection("Treino").get().await()
            val treinos = querySnapshot.toObjects(Treino::class.java)
            RepositoryResult.Success(treinos)
        } catch (e: Exception) {
            RepositoryResult.Error(e)
        }
    }

    override suspend fun updateTreino(treino: Treino): RepositoryResult<Unit> {
        return try {
            firestore.collection("Treino").document(treino.nome.toString()).set(treino).await()
            RepositoryResult.Success(Unit)
        } catch (e: Exception) {
            RepositoryResult.Error(e)
        }
    }

    override suspend fun deleteTreino(treinoId: Int): RepositoryResult<Unit> {
        return try {
            firestore.collection("Treino").document(treinoId.toString()).delete().await()
            RepositoryResult.Success(Unit)
        } catch (e: Exception) {
            RepositoryResult.Error(e)
        }
    }

    override suspend fun addExercicio(exercicio: Exercicio): RepositoryResult<Unit> {
        return try {
            Log.d(TAG, "Attempting to add exercicio: $exercicio")
            firestore.collection("Exercicio").add(exercicio).await()
            Log.d(TAG, "Exercicio added successfully.")
            RepositoryResult.Success(Unit)
        } catch (e: Exception) {
            Log.e(TAG, "Error adding exercicio: $exercicio", e)
            RepositoryResult.Error(e)
        }
    }

    override suspend fun getExercicios(): RepositoryResult<List<Exercicio>> {
        return try {
            val querySnapshot = firestore.collection("Exercicio").get().await()
            val exercicios = querySnapshot.toObjects(Exercicio::class.java)
            RepositoryResult.Success(exercicios)
        } catch (e: Exception) {
            RepositoryResult.Error(e)
        }
    }

    override suspend fun updateExercicio(exercicio: Exercicio): RepositoryResult<Unit> {
        return try {
            firestore.collection("Exercicio").document(exercicio.nome.toString()).set(exercicio)
                .await()
            RepositoryResult.Success(Unit)
        } catch (e: Exception) {
            RepositoryResult.Error(e)
        }
    }

    override suspend fun deleteExercicio(exercicioId: Int): RepositoryResult<Unit> {
        return try {
            firestore.collection("Exercicio").document(exercicioId.toString()).delete().await()
            RepositoryResult.Success(Unit)
        } catch (e: Exception) {
            RepositoryResult.Error(e)
        }
    }

}