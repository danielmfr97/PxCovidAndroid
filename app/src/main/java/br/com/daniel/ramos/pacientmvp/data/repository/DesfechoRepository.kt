package br.com.daniel.ramos.pacientmvp.data.repository

import android.util.Log
import br.com.daniel.ramos.pacientmvp.data.model.desfecho.Desfecho
import br.com.daniel.ramos.pacientmvp.data.model.plaquetas.Plaqueta
import br.com.daniel.ramos.pacientmvp.data.network.PacientService
import io.realm.RealmResults
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DesfechoRepository : RealmRepository<Desfecho>(Desfecho::class.java) {
    fun getDataSavedOnDB(): RealmResults<Desfecho> {
        return realm.where(clazz).findAll()
    }

    suspend fun getDesfechoData(): List<Desfecho>? {
        return getDesfechosFromDB()
    }

    private suspend fun getDesfechosFromDB(): List<Desfecho>? {
        var desfechoList: List<Desfecho>? = realm.where(clazz).findAll()
        if (desfechoList.isNullOrEmpty()) {
            desfechoList = getDesfechosFromAPI()
            atualizarObject(object : AtualizarListener {
                override fun atualizar() {
                    desfechoList?.forEach {
                        val desfecho: Desfecho = novo(it.id)
                        desfecho.alias = it.alias
                        desfecho.label = it.label
                    }
                }
            })
        }
        return desfechoList
    }

    private suspend fun getDesfechosFromAPI(): List<Desfecho>? {
        return withContext(Dispatchers.IO) {
            val response = PacientService.service.getDesfechoData()
            Log.i("TESTE", response.toString())
            response.body()?.results
        }
    }
}