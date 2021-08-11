package br.com.daniel.ramos.pacientmvp.data.model.plaquetas

import com.google.gson.annotations.SerializedName

data class PlaquetaResponse(
    val alias: String,
    @SerializedName("content")
    val results: List<Plaqueta>,
    val label: String,
    val total: Int
)