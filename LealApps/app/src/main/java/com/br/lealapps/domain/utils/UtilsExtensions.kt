package com.br.lealapps.domain.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

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

fun Long.toBrazilianDateFormat(
    pattern: String = "dd/MM/yyyy"
): String {
    val date = Date(this)
    val formatter = SimpleDateFormat(
        pattern, Locale("pt-br")
    ).apply {
        timeZone = TimeZone.getTimeZone("GMT")
    }
    return formatter.format(date)
}

fun String.toBrazilianDate(
    pattern: String = "dd/MM/yyyy"
): Date {
    val formatter = SimpleDateFormat(
        pattern, Locale("pt-br")
    )
    return formatter.parse(this)!!
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
