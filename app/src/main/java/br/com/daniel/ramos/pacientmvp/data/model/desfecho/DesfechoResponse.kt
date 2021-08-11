package br.com.daniel.ramos.pacientmvp.data.model.desfecho

import com.google.gson.annotations.SerializedName

data class DesfechoResponse(
    val alias: String,
    @SerializedName("content")
    val results: List<Desfecho>,
    val label: String,
    val total: Int
)