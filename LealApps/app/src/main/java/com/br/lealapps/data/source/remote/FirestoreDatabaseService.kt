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
            firestore.collection("Treino").document(treino.nome).set(treino).await()
            RepositoryResult.Success(Unit)
        } catch (e: Exception) {
            RepositoryResult.Error(e)
        }
    }

    override suspend fun deleteTreino(treinoName: String): RepositoryResult<Unit> {
        return try {
            val querySnapshot = firestore.collection("Treino")
                .whereEqualTo("nome", treinoName)
                .get()
                .await()

            if (!querySnapshot.isEmpty) {
                val documentReference = querySnapshot.documents[0].reference
                documentReference.delete().await()
                Log.d(TAG, "Treino deleted successfully.")
            } else {
                Log.d(TAG, "Treino not found for excluding.")
            }

            RepositoryResult.Success(Unit)
        } catch (e: Exception) {
            Log.e(TAG, "Error deleting treino: $treinoName", e)
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
            firestore.collection("Exercicio").document(exercicio.nome).set(exercicio)
                .await()
            RepositoryResult.Success(Unit)
        } catch (e: Exception) {
            RepositoryResult.Error(e)
        }
    }

    override suspend fun deleteExercicio(exercicioName: String): RepositoryResult<Unit> {
        return try {
            val querySnapshot = firestore.collection("Exercicio")
                .whereEqualTo("nome", exercicioName)
                .get()
                .await()

            if (!querySnapshot.isEmpty) {
                val documentReference = querySnapshot.documents[0].reference
                documentReference.delete().await()
                Log.d(TAG, "Exercicio deleted successfully.")
            } else {
                Log.d(TAG, "Exercicio not found for excluding.")
            }

            RepositoryResult.Success(Unit)
        } catch (e: Exception) {
            Log.e(TAG, "Error deleting exercicio: $exercicioName", e)
            RepositoryResult.Error(e)
        }
    }
}