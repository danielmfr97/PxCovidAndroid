package br.com.daniel.ramos.pacientmvp.data.repository

import br.com.daniel.ramos.pacientmvp.MainActivity
import br.com.daniel.ramos.pacientmvp.data.model.Info
import br.com.daniel.ramos.pacientmvp.data.model.Referencia
import br.com.daniel.ramos.pacientmvp.utils.JsonUtils
import org.json.JSONObject

class MetodologiaRepository {
    var jsonData: String = JsonUtils
        .getJsonFromAssets(MainActivity.instance!!.applicationContext, "dados.json")
        .toString()

    fun getIA(): Info {
        val jsonObject = JSONObject(jsonData).getJSONObject("inteligencia_artificial")
        val jsonArray = jsonObject.getJSONArray("referencias")
        val referencias = mutableListOf<Referencia>()
        for (i in 0 until jsonArray.length()) {
            val tempObj = jsonArray.getJSONObject(i)
            referencias.add(Referencia(tempObj.getString("ref"), tempObj.getString("link")))
        }
        return Info(referencias, jsonObject.getString("resumo"))
    }

    fun getAlgoritmo(): Info {
        val jsonObject = JSONObject(jsonData).getJSONObject("algoritmo")
        val jsonArray = jsonObject.getJSONArray("referencias")
        val referencias = mutableListOf<Referencia>()
        for (i in 0 until jsonArray.length()) {
            val tempObj = jsonArray.getJSONObject(i)
            referencias.add(Referencia(tempObj.getString("ref"), tempObj.getString("link")))
        }
        return Info(referencias, jsonObject.getString("resumo"))
    }

    fun getContribuir(): Info {
        val jsonObject = JSONObject(jsonData).getJSONObject("formas_contribuir")
        val jsonArray = jsonObject.getJSONArray("referencias")
        val referencias = mutableListOf<Referencia>()
        for (i in 0 until jsonArray.length()) {
            val tempObj = jsonArray.getJSONObject(i)
            referencias.add(Referencia(tempObj.getString("ref"), tempObj.getString("link")))
        }
        return Info(referencias, jsonObject.getString("resumo"))
    }

    fun getMetricas(): Info {
        val jsonObject = JSONObject(jsonData).getJSONObject("metricas")
        val jsonArray = jsonObject.getJSONArray("referencias")
        val referencias = mutableListOf<Referencia>()
        for (i in 0 until jsonArray.length()) {
            val tempObj = jsonArray.getJSONObject(i)
            referencias.add(Referencia(tempObj.getString("ref"), tempObj.getString("link")))
        }
        return Info(referencias, jsonObject.getString("resumo"))
    }

    fun getProjeto(): Info {
        val jsonObject = JSONObject(jsonData).getJSONObject("sobre_projeto")
        val jsonArray = jsonObject.getJSONArray("referencias")
        val referencias = mutableListOf<Referencia>()
        for (i in 0 until jsonArray.length()) {
            val tempObj = jsonArray.getJSONObject(i)
            referencias.add(Referencia(tempObj.getString("ref"), tempObj.getString("link")))
        }
        return Info(referencias, jsonObject.getString("resumo"))
    }

}