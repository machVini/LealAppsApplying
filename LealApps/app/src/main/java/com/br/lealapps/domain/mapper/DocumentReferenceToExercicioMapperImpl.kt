package com.br.lealapps.domain.mapper

import com.br.lealapps.data.source.model.Exercicio
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class DocumentReferenceToExercicioMapperImpl() : DocumentReferenceToExercicioMapper {
    override suspend fun mapFrom(documentReference: DocumentReference): Exercicio? {
        return suspendCancellableCoroutine { continuation ->
            documentReference.get()
                .addOnCompleteListener { task: Task<DocumentSnapshot> ->
                    if (task.isSuccessful) {
                        val document = task.result
                        if (document != null && document.exists()) {
                            val exercicio = document.toObject(Exercicio::class.java)
                            continuation.resume(exercicio)
                        } else {
                            continuation.resume(null)
                        }
                    } else {
                        continuation.resume(null)
                    }
                }
        }
    }


}