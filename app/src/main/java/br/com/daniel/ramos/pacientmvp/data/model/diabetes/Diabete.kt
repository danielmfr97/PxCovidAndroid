package br.com.daniel.ramos.pacientavaliation.data.model.diabetes

import br.com.daniel.ramos.pacientmvp.view.adapters.ModelDisplayName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey


open class Diabete : RealmObject(), ModelDisplayName {
    @PrimaryKey
    var id: Int = 0
    var alias: String = ""
    var label: String = ""

    override val displayName: String
        get() = label
}