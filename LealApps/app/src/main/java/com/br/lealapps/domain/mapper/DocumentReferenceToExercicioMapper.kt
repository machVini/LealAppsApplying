package com.br.lealapps.domain.mapper

import com.br.lealapps.data.source.model.Exercicio
import com.google.firebase.firestore.DocumentReference

interface DocumentReferenceToExercicioMapper {
    suspend fun mapFrom(documentReference: DocumentReference): Exercicio?
}