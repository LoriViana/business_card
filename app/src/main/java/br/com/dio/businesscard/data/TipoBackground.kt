package br.com.dio.businesscard.data

import java.io.Serializable

data class TipoBackground(
    val link:String,
    val cor: String? = null
):Serializable
