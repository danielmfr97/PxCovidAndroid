package br.com.daniel.ramos.pacientmvp.data.model

import io.realm.RealmObject
import io.realm.RealmResults

data class Info(
    var referencias: List<Referencia> = listOf(),
    var resumo: String = ""
)