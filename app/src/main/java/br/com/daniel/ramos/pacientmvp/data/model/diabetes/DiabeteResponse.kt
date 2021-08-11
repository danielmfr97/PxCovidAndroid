package br.com.daniel.ramos.pacientavaliation.data.model.diabetes

import com.google.gson.annotations.SerializedName


data class DiabeteResponse(
    val label: String,
    val alias:String,
    @SerializedName("content")
    val results: List<Diabete>,
    val total: Int
)