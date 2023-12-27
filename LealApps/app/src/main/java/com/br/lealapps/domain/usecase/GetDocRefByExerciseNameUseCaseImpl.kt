package com.br.lealapps.domain.usecase

import com.br.lealapps.data.repository.FitnessRepository
import com.google.firebase.firestore.DocumentReference
import javax.inject.Inject

class GetDocRefByExerciseNameUseCaseImpl @Inject constructor(
    private val repository: FitnessRepository
) : GetDocRefByExerciseNameUseCase {
    override suspend operator fun invoke(exercicioName: String): DocumentReference? {
        return repository.getDocReferenceByName(exercicioName)
    }

}
