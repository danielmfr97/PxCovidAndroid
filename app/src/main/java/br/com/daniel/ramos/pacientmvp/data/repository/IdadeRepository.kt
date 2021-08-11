package br.com.daniel.ramos.pacientmvp.data.repository

import android.util.Log
import br.com.daniel.ramos.pacientmvp.data.model.idade.Idade
import br.com.daniel.ramos.pacientmvp.data.model.plaquetas.Plaqueta
import br.com.daniel.ramos.pacientmvp.data.network.PacientService
import io.realm.RealmResults
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class IdadeRepository : RealmRepository<Idade>(Idade::class.java) {
    fun getDataSavedOnDB(): RealmResults<Idade> {
        return realm.where(clazz).findAll()
    }

    suspend fun getIdadeData(): List<Idade>? {
        return getIdadesFromDB()
    }

    private suspend fun getIdadesFromDB(): List<Idade>? {
        var idadeList: List<Idade>? = realm.where(clazz).findAll()
        if (idadeList.isNullOrEmpty()) {
            idadeList = getIdadesFromAPI()
            atualizarObject(object : AtualizarListener {
                override fun atualizar() {
                    idadeList?.forEach {
                        val idade: Idade = novo(it.id)
                        idade.alias = it.alias
                        idade.label = it.label
                    }
                }
            })
        }
        return idadeList
    }

    private suspend fun getIdadesFromAPI(): List<Idade>? {
        return withContext(Dispatchers.IO) {
            val response = PacientService.service.getIdadeData()
            Log.i("TESTE", response.toString())
            response.body()?.results
        }
    }
}