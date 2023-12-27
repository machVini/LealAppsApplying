package com.br.lealapps.domain.utils

import java.util.Date

fun Date?.toTreinoDetailData(): String {
    val weekDay = when (this?.day) {
        1 -> DiaDaSemana.SEGUNDA.nome
        2 -> DiaDaSemana.TERCA.nome
        3 -> DiaDaSemana.QUARTA.nome
        4 -> DiaDaSemana.QUINTA.nome
        5 -> DiaDaSemana.SEXTA.nome
        6 -> DiaDaSemana.SABADO.nome
        else -> DiaDaSemana.DOMINGO.nome
    }
    return "$weekDay - ${this?.date}/${this?.month?.plus(1)}/${this?.year?.plus(1900)}"
}

enum class DiaDaSemana(val nome: String){
    SEGUNDA("Segunda-Feira"),
    TERCA("Terça-Feira"),
    QUARTA("Quarta-Feira"),
    QUINTA("Quinta-Feira"),
    SEXTA("Sexta-Feira"),
    SABADO("Sábado"),
    DOMINGO("Domingo"),
}
