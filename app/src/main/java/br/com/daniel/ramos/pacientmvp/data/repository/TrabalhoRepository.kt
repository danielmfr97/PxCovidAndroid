package br.com.daniel.ramos.pacientmvp.data.repository

import android.util.Log
import br.com.daniel.ramos.pacientmvp.MainActivity
import br.com.daniel.ramos.pacientmvp.data.model.plaquetas.Plaqueta
import br.com.daniel.ramos.pacientmvp.data.model.trabalho.Trabalho
import br.com.daniel.ramos.pacientmvp.utils.JsonUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.realm.RealmResults
import java.lang.reflect.Type


class TrabalhoRepository : RealmRepository<Trabalho>(Trabalho::class.java) {
    fun getTrabalhos(): RealmResults<Trabalho> {
        var dados = realm.where(clazz).findAll()

        if (dados == null || dados.isEmpty()) {
            val jsonFileString: String =
                JsonUtils.getJsonFromAssets(
                    MainActivity.instance!!.applicationContext,
                    "artigos.json"
                )
                    .toString()
            Log.i("data", jsonFileString)
            val gson = Gson()
            val listUserType: Type = object : TypeToken<List<Trabalho?>?>() {}.type
            val trabalhos: List<Trabalho> = gson.fromJson(jsonFileString, listUserType)
            for (job in trabalhos) {
                realm.beginTransaction()
                realm.copyToRealm(job)
                realm.commitTransaction()
            }
        }
        return dados
    }

}