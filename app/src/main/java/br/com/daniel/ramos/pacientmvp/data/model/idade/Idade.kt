package br.com.daniel.ramos.pacientmvp.data.model.idade

import br.com.daniel.ramos.pacientmvp.view.adapters.ModelDisplayName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Idade : RealmObject(), ModelDisplayName {
    @PrimaryKey
    var id: Int = 0
    var alias: String = ""
    var label: String = ""
    override val displayName: String
        get() = label
}