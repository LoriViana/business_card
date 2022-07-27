package br.com.dio.businesscard.data

import java.io.Serializable

data class TipoBackground(
    var link:String,
    val cor: String? = null
):Serializable
