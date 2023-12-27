package com.br.lealapps.data.source.model

import com.google.firebase.firestore.PropertyName

data class ExercicioResponse(
    @PropertyName("nome")
    val nome: String = "",

    @PropertyName("imagem")
    val imagem: String = "",

    @PropertyName("observacoes")
    val observacoes: String = "",
)
