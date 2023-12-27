package com.br.lealapps.domain.usecase

import com.br.lealapps.data.repository.FitnessRepository
import com.br.lealapps.domain.model.Exercicio
import com.google.firebase.firestore.DocumentReference
import javax.inject.Inject

class GetExercicioByDocRefUseCaseImpl @Inject constructor(
    private val repository: FitnessRepository
) : GetExercicioByDocRefUseCase {
    override suspend fun invoke(documentReference: DocumentReference): Exercicio? {
        return repository.getExercicioByDocRef(documentReference)
    }


}