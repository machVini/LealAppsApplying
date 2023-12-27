package com.br.lealapps.domain.usecase

import com.br.lealapps.domain.model.Exercicio
import com.google.firebase.firestore.DocumentReference

interface GetExercicioByDocRefUseCase {
    suspend operator fun invoke(documentReference: DocumentReference): Exercicio?
}