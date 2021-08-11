package br.com.daniel.ramos.pacientmvp.data.repository

import android.util.Log
import br.com.daniel.ramos.pacientavaliation.data.model.leucocito.Leucocito
import br.com.daniel.ramos.pacientmvp.data.model.plaquetas.Plaqueta
import br.com.daniel.ramos.pacientmvp.data.network.PacientService
import io.realm.RealmResults
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PlaquetaRepository : RealmRepository<Plaqueta>(Plaqueta::class.java) {
    fun getDataSavedOnDB(): RealmResults<Plaqueta> {
        return realm.where(clazz).findAll()
    }

    suspend fun getPlaquetaData(): List<Plaqueta>? {
        return getPlaquetasFromDB()
    }

    private suspend fun getPlaquetasFromDB(): List<Plaqueta>? {
        var plaquetaList: List<Plaqueta>? = realm.where(clazz).findAll()
        if (plaquetaList.isNullOrEmpty()) {
            plaquetaList = getPlaquetasFromAPI()
            atualizarObject(object : AtualizarListener {
                override fun atualizar() {
                    plaquetaList?.forEach {
                        val plaqueta: Plaqueta = novo(it.id)
                        plaqueta.alias = it.alias
                        plaqueta.label = it.label
                    }
                }
            })
        }
        return plaquetaList
    }

    private suspend fun getPlaquetasFromAPI(): List<Plaqueta>? {
        return withContext(Dispatchers.IO) {
            val response = PacientService.service.getPlaquetaData()
            Log.i("TESTE", response.toString())
            response.body()?.results
        }
    }
}