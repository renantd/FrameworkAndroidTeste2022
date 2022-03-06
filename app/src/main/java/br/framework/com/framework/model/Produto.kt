package br.framework.com.framework.model

import com.google.gson.annotations.SerializedName

data class Produto(

    var name: String,
    var price: Double,
    var foto: Int,
    var descricao: String

)