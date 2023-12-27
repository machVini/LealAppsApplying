package com.br.lealapps.domain.model

import java.util.Date

data class Treino(
    var nome: String = "",
    var descricao: String = "",
    var data: Date? = null,
    var exercicios: List<Exercicio> = emptyList()
)
