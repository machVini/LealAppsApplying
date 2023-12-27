package com.br.lealapps.domain.usecase

import com.google.firebase.firestore.DocumentReference

interface GetDocRefByExerciseNameUseCase {
    suspend operator fun invoke(exercicioName: String): DocumentReference?
}