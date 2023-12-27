package com.br.lealapps.data.source.remote

import android.content.ContentValues.TAG
import android.util.Log
import com.br.lealapps.data.source.model.ExercicioResponse
import com.br.lealapps.data.source.model.TreinoResponse
import com.br.lealapps.data.source.model.result.RepositoryResult
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirestoreFitnessService @Inject constructor(
    private val firestore: FirebaseFirestore
) : FitnessDataSource {
    override suspend fun addTreino(treino: TreinoResponse): RepositoryResult<Unit> {
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

    override suspend fun getTreinos(): RepositoryResult<List<TreinoResponse>> {
        return try {
            val querySnapshot = firestore.collection("Treino").get().await()
            val treinos = querySnapshot.toObjects(TreinoResponse::class.java)
            RepositoryResult.Success(treinos)
        } catch (e: Exception) {
            RepositoryResult.Error(e)
        }
    }

    override suspend fun updateTreino(treino: TreinoResponse): RepositoryResult<Unit> {
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


    override suspend fun addExercicio(exercicio: ExercicioResponse): RepositoryResult<Unit> {
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

    override suspend fun getExercicios(): RepositoryResult<List<ExercicioResponse>> {
        return try {
            val querySnapshot = firestore.collection("Exercicio").get().await()
            val exercicios = querySnapshot.toObjects(ExercicioResponse::class.java)
            RepositoryResult.Success(exercicios)
        } catch (e: Exception) {
            RepositoryResult.Error(e)
        }
    }

    override suspend fun updateExercicio(exercicio: ExercicioResponse): RepositoryResult<Unit> {
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

    override suspend fun getDocReferenceByName(exercicioName: String): DocumentReference? {
        val querySnapshot = firestore.collection("Exercicio")
            .whereEqualTo("nome", exercicioName)
            .get()
            .await()

        return if (querySnapshot.isEmpty) null else querySnapshot.documents[0].reference
    }

    override suspend fun getExercicioByDocRef(docRef: DocumentReference): ExercicioResponse? {
        return try {
            val documentSnapShot = docRef.get().await()
            if (documentSnapShot != null && documentSnapShot.exists()) {
                documentSnapShot.toObject(ExercicioResponse::class.java)
            } else null
        } catch (_: Exception) {
            null
        }
    }
}