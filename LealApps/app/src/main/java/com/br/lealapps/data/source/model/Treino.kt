package com.br.lealapps.data.source.model

import com.google.firebase.firestore.DocumentReference
import java.sql.Timestamp

data class Treino(
    val nome: Int,
    val descricao: String,
    val data: Timestamp,
    val exercicios: List<DocumentReference> // Lista de referências para exercícios
)

