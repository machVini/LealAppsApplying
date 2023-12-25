package com.br.lealapps.domain.model

sealed class DatabaseError(val errorMessage: String): Exception() {
    class ExercisesNotFoundError(treinoName: String) : DatabaseError("Erro ao obter exercícios pelo treino: $treinoName")
}