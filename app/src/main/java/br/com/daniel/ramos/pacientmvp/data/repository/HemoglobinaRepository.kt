package br.com.daniel.ramos.pacientmvp.data.repository

import android.util.Log
import br.com.daniel.ramos.pacientmvp.data.model.hemoglobina.Hemoglobina
import br.com.daniel.ramos.pacientmvp.data.model.plaquetas.Plaqueta
import br.com.daniel.ramos.pacientmvp.data.network.PacientService
import io.realm.RealmResults
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HemoglobinaRepository : RealmRepository<Hemoglobina>(Hemoglobina::class.java) {
    fun getDataSavedOnDB(): RealmResults<Hemoglobina> {
        return realm.where(clazz).findAll()
    }

    suspend fun getHemoglobinaData(): List<Hemoglobina>? {
        return getHemoglobinasFromDB()
    }

    private suspend fun getHemoglobinasFromDB(): List<Hemoglobina>? {
        var hemoglobinaList: List<Hemoglobina>? = realm.where(clazz).findAll()
        if (hemoglobinaList.isNullOrEmpty()) {
            hemoglobinaList = getHemoglobinasFromAPI()
            atualizarObject(object : AtualizarListener {
                override fun atualizar() {
                    hemoglobinaList?.forEach {
                        val hemoglobina: Hemoglobina = novo(it.id)
                        hemoglobina.alias = it.alias
                        hemoglobina.label = it.label
                    }
                }
            })
        }
        return hemoglobinaList
    }

    private suspend fun getHemoglobinasFromAPI(): List<Hemoglobina>? {
        return withContext(Dispatchers.IO) {
            val response = PacientService.service.getHemoglobinaData()
            Log.i("TESTE", response.toString())
            response.body()?.results
        }
    }
}