package br.com.daniel.ramos.pacientmvp.data.model.hemoglobina

import com.google.gson.annotations.SerializedName

data class HemoglobinaResponse(
    val alias: String,
    @SerializedName("content")
    val results: List<Hemoglobina>,
    val label: String,
    val total: Int
)