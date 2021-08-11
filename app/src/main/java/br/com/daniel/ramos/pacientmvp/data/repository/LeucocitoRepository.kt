package br.com.daniel.ramos.pacientmvp.data.repository

import android.util.Log
import br.com.daniel.ramos.pacientavaliation.data.model.diabetes.Diabete
import br.com.daniel.ramos.pacientavaliation.data.model.leucocito.Leucocito
import br.com.daniel.ramos.pacientmvp.data.network.PacientService
import io.realm.RealmResults
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LeucocitoRepository : RealmRepository<Leucocito>(Leucocito::class.java) {

    fun getDataSavedOnDB(): RealmResults<Leucocito> {
        return realm.where(clazz).findAll()
    }

    suspend fun getLeucocitoData(): List<Leucocito>? {
        return getLeucocitosFromDB()
    }

    private suspend fun getLeucocitosFromDB(): List<Leucocito>? {
        var leucocitoList: List<Leucocito>? = realm.where(clazz).findAll()
        if (leucocitoList.isNullOrEmpty()) {
            leucocitoList = getLeucocitosFromAPI()
            atualizarObject(object : AtualizarListener {
                override fun atualizar() {
                    leucocitoList?.forEach {
                        val leucocito: Leucocito = novo(it.id)
                        leucocito.alias = it.alias
                        leucocito.label = it.label
                    }
                }
            })
        }
        return leucocitoList
    }

    private suspend fun getLeucocitosFromAPI(): List<Leucocito>? {
        return withContext(Dispatchers.IO) {
            val response = PacientService.service.getLeucocitoData()
            Log.i("TESTE", response.toString())
            response.body()?.results
        }
    }
}