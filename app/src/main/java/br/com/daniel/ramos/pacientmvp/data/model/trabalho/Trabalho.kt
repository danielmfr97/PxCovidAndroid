package br.com.daniel.ramos.pacientmvp.data.model.trabalho

import io.realm.RealmObject

open class Trabalho : RealmObject() {
    var dataArtigo: String = ""
    var tituloArtigo: String = ""
    var revistaArtigo: String = ""
    var resumoArtigo: String = ""
    var linkArtigo: String = ""
}
