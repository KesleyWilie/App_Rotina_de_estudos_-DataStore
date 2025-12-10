package com.kw.rotinadeestudoscompose.data

import kotlinx.serialization.Serializable

//@Serializable
data class Atividade(
    val id: Int = 0,
    val dia: String,
    val descricao: String
)