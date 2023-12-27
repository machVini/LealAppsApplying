package com.br.lealapps.domain.usecase

import com.br.lealapps.data.repository.FitnessRepository
import com.br.lealapps.domain.model.Exercicio
import com.google.firebase.firestore.DocumentReference

class GetExercicioByDocRefUseCaseImpl (
    private val repository: FitnessRepository
) : GetExercicioByDocRefUseCase {
    override suspend fun invoke(documentReference: DocumentReference): Exercicio? {
        return repository.getExercicioByDocRef(documentReference)
    }


}