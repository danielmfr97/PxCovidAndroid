package br.com.daniel.ramos.pacientmvp.data.repository

import android.util.Log
import br.com.daniel.ramos.pacientavaliation.data.model.diabetes.Diabete
import br.com.daniel.ramos.pacientmvp.data.network.PacientService
import io.realm.RealmResults
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DiabetesRepository : RealmRepository<Diabete>(Diabete::class.java) {
    fun getDataSavedOnDB(): RealmResults<Diabete> {
        return realm.where(clazz).findAll()
    }

    suspend fun getDiabetesData(): List<Diabete>? {
        return getDiabetesFromDB()
    }

    private suspend fun getDiabetesFromDB(): List<Diabete>? {
        var diabeteList: List<Diabete>? = realm.where(clazz).findAll()
        if (diabeteList.isNullOrEmpty()) {
            diabeteList = getDiabetesFromAPI()
            atualizarObject(object : AtualizarListener {
                override fun atualizar() {
                    diabeteList?.forEach {
                        val diabete: Diabete = novo(it.id)
                        diabete.alias = it.alias
                        diabete.label = it.label
                    }
                }
            })
        }
        return diabeteList
    }

    private suspend fun getDiabetesFromAPI(): List<Diabete>? {
        return withContext(Dispatchers.IO) {
            val response = PacientService.service.getDiabetesData()
            Log.i("TESTE", response.toString())
            response.body()?.results
        }
    }
}