package com.br.lealapps.data.source.model

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.PropertyName
import kotlinx.parcelize.RawValue
import java.util.Date


data class TreinoResponse(
    @PropertyName("nome")
    var nome: String = "",

    @PropertyName("descricao")
    var descricao: String = "",

    @PropertyName("data")
    var data: Date? = null,

    @PropertyName("exercicios")
    var exercicios: @RawValue List<DocumentReference> = emptyList()
)

