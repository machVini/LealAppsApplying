package com.br.lealapps.domain.mapper

import com.br.lealapps.data.source.model.ExercicioResponse
import com.br.lealapps.data.source.model.TreinoResponse
import com.br.lealapps.domain.model.Exercicio
import com.br.lealapps.domain.model.Treino
import com.google.firebase.firestore.DocumentReference
import kotlinx.parcelize.RawValue
import javax.inject.Inject

class FitnessResponseMapperImpl : FitnessResponseMapper {

    override suspend fun mapTreinoFromResponse(response: TreinoResponse, exercicios: List<Exercicio>): Treino {
        return Treino(
            nome = response.nome,
            descricao = response.descricao,
            data = response.data,
            exercicios = exercicios
        )
    }

    override suspend fun mapTreinoToResponse(domain: Treino, exercicios: List<DocumentReference>): TreinoResponse {
        return TreinoResponse(
            nome = domain.nome,
            descricao = domain.descricao,
            data = domain.data,
            exercicios = exercicios
        )
    }

    override suspend fun mapExercicioFromResponse(response: ExercicioResponse): Exercicio {
        return Exercicio(
            nome = response.nome,
            imagem = response.imagem,
            observacoes = response.observacoes,
        )
    }

    override suspend fun mapExercicioToResponse(domain: Exercicio): ExercicioResponse {
        return ExercicioResponse(
            nome = domain.nome,
            imagem = domain.imagem,
            observacoes = domain.observacoes,
        )
    }
}