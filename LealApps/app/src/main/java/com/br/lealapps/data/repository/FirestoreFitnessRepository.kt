package com.br.lealapps.data.repository

import com.br.lealapps.data.source.model.result.RepositoryResult
import com.br.lealapps.data.source.remote.FitnessDataSource
import com.br.lealapps.domain.mapper.FitnessResponseMapper
import com.br.lealapps.domain.model.Exercicio
import com.br.lealapps.domain.model.Treino
import com.google.firebase.firestore.DocumentReference

class FirestoreFitnessRepository (
    private val dataSource: FitnessDataSource,
    private val fitnessResponseMapper: FitnessResponseMapper,
) : FitnessRepository {
    override suspend fun addTreino(treino: Treino): RepositoryResult<Unit> {
        return dataSource.addTreino(
            fitnessResponseMapper.mapTreinoToResponse(
                domain = treino,
                exercicios = mapExercicioListToDocRefList(treino.exercicios)
            )
        )
    }

    override suspend fun getTreinos(): RepositoryResult<List<Treino>> {
        return try {
            val treinosResponse = dataSource.getTreinos()

            if (treinosResponse is RepositoryResult.Success) {
                val treinos = treinosResponse.data.map { treinoResponse ->
                    fitnessResponseMapper.mapTreinoFromResponse(
                        response = treinoResponse,
                        exercicios = mapDocRefListToExercicioList(treinoResponse.exercicios)
                    )
                }
                RepositoryResult.Success(treinos)
            } else {
                RepositoryResult.Error(Exception("Error getting treinos"))
            }
        } catch (e: Exception) {
            RepositoryResult.Error(e)
        }
    }


    override suspend fun updateTreino(treinoAntigoName: String, treinoNovo: Treino): RepositoryResult<Unit> {
        return dataSource.updateTreino(
            treinoAntigoName = treinoAntigoName,
            treinoNovo = fitnessResponseMapper.mapTreinoToResponse(
                domain = treinoNovo,
                exercicios = mapExercicioListToDocRefList(treinoNovo.exercicios)
            )
        )
    }

    override suspend fun deleteTreino(treinoName: String): RepositoryResult<Unit> {
        return dataSource.deleteTreino(treinoName)
    }

    override suspend fun addExercicio(exercicio: Exercicio): RepositoryResult<Unit> {
        return dataSource.addExercicio(fitnessResponseMapper.mapExercicioToResponse(exercicio))
    }

    override suspend fun getExercicios(): RepositoryResult<List<Exercicio>> {
        return try {
            val exerciciosResponse = dataSource.getExercicios()

            if (exerciciosResponse is RepositoryResult.Success) {
                val exercicios = exerciciosResponse.data.map { exercicioResponse ->
                    fitnessResponseMapper.mapExercicioFromResponse(exercicioResponse)
                }
                RepositoryResult.Success(exercicios)
            } else {
                RepositoryResult.Error(Exception("Error getting exercicios"))
            }
        } catch (e: Exception) {
            RepositoryResult.Error(e)
        }
    }

    override suspend fun updateExercicio(exercicio: Exercicio): RepositoryResult<Unit> {
        return dataSource.updateExercicio(fitnessResponseMapper.mapExercicioToResponse(exercicio))
    }

    override suspend fun deleteExercicio(exercicioName: String): RepositoryResult<Unit> {
        return dataSource.deleteExercicio(exercicioName)
    }

    override suspend fun getDocReferenceByName(exercicioName: String): DocumentReference? {
        return dataSource.getDocReferenceByName(exercicioName)
    }

    override suspend fun getExercicioByDocRef(docRef: DocumentReference): Exercicio {
        return fitnessResponseMapper.mapExercicioFromResponse(dataSource.getExercicioByDocRef(docRef)!!)
    }

    private suspend fun mapDocRefListToExercicioList(
        documentReferences: List<DocumentReference>
    ): List<Exercicio> {
        val exerciciosList = mutableListOf<Exercicio>()

        for (documentReference in documentReferences) {
            val exercicio = getExercicioByDocRef(documentReference)
            exercicio.let { exerciciosList.add(it) }
        }

        return exerciciosList
    }

    suspend fun mapExercicioListToDocRefList(
        exercicios: List<Exercicio>
    ): List<DocumentReference> {
        val docRefList = mutableListOf<DocumentReference>()

        for (exercicio in exercicios) {
            val docRef = getDocReferenceByName(exercicio.nome)
            docRef?.let { docRefList.add(it) }
        }

        return docRefList
    }
}