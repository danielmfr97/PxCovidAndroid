package br.com.daniel.ramos.pacientmvp.data.model.leucocito

import br.com.daniel.ramos.pacientavaliation.data.model.leucocito.Leucocito
import com.google.gson.annotations.SerializedName

data class LeucocitoResponse(
    val alias: String,
    @SerializedName("content")
    val results: List<Leucocito>,
    val label: String,
    val total: Int
)