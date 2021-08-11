package br.com.daniel.ramos.pacientmvp.data.model.idade

import com.google.gson.annotations.SerializedName

data class IdadeResponse(
    val alias: String,
    @SerializedName("content")
    val results: List<Idade>,
    val label: String,
    val total: Int
)