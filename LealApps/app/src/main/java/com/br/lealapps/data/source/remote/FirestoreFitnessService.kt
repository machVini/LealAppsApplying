package com.br.lealapps.data.source.remote

import android.content.ContentValues.TAG
import android.net.Uri
import android.util.Log
import androidx.core.net.toUri
import com.br.lealapps.data.source.model.ExercicioResponse
import com.br.lealapps.data.source.model.TreinoResponse
import com.br.lealapps.data.source.model.result.RepositoryResult
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await

class FirestoreFitnessService(
    private val firestore: FirebaseFirestore,
    private val storage: FirebaseStorage,
) : FitnessDataSource {
    override suspend fun addTreino(treino: TreinoResponse): RepositoryResult<Unit> {
        return try {
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

    override suspend fun updateTreino(
        treinoAntigoName: String,
        treinoNovo: TreinoResponse
    ): RepositoryResult<Unit> {
        return try {
            val querySnapshot = firestore.collection("Treino")
                .whereEqualTo("nome", treinoAntigoName)
                .get()
                .await()

            if (!querySnapshot.isEmpty) {
                val documentReference = querySnapshot.documents[0].reference
                documentReference.update(
                    "nome", treinoNovo.nome,
                    "descricao", treinoNovo.descricao,
                    "data", treinoNovo.data,
                    "exercicios", treinoNovo.exercicios,
                ).await()
                Log.d(TAG, "Treino updated successfully.")
            }
            RepositoryResult.Success(Unit)
        } catch (e: Exception) {
            Log.e(TAG, "Error updating treino: ${treinoNovo.nome}", e)
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
            val imagemUrl = uploadImage(exercicio.imagem.toUri())

            val exercicioToAdd = exercicio.copy(
                imagem = imagemUrl
            )

            firestore.collection("Exercicio").add(exercicioToAdd).await()
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

    override suspend fun updateExercicio(
        exercicioAntigoName: String,
        exercicioNovo: ExercicioResponse
    ): RepositoryResult<Unit> {
        return try {
            val imagemUrl = uploadImage(exercicioNovo.imagem.toUri())

            val exercicioToUpdate = exercicioNovo.copy(
                imagem = imagemUrl
            )

            val querySnapshot = firestore.collection("Exercicio")
                .whereEqualTo("nome", exercicioAntigoName)
                .get()
                .await()

            if (!querySnapshot.isEmpty) {
                val documentReference = querySnapshot.documents[0].reference
                documentReference.update(
                    "nome", exercicioToUpdate.nome,
                    "imagem", exercicioToUpdate.imagem,
                    "observacoes", exercicioToUpdate.observacoes
                ).await()
                Log.d(TAG, "Exercicio updated successfully.")
            }
            RepositoryResult.Success(Unit)
        } catch (e: Exception) {
            Log.e(TAG, "Error updating exercicio: ${exercicioNovo.nome}", e)
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

    private suspend fun uploadImage(imagemUri: Uri): String {
        return try {
            val storageRef = storage.reference
            val imageRef = storageRef.child("exercicios/${imagemUri.lastPathSegment}")

            val uploadTask = imageRef.putFile(imagemUri).await()
            if (uploadTask.task.isSuccessful) {
                val downloadUrl = imageRef.downloadUrl.await()
                downloadUrl.toString()
            } else {
                imagemUri.toString()
            }
        } catch (e: Exception) {
            imagemUri.toString()
        }
    }

}