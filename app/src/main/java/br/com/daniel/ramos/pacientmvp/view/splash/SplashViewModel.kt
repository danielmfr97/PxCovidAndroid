package br.com.daniel.ramos.pacientmvp.view.splash

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.daniel.ramos.pacientavaliation.data.model.diabetes.Diabete
import br.com.daniel.ramos.pacientavaliation.data.model.leucocito.Leucocito
import br.com.daniel.ramos.pacientmvp.data.model.desfecho.Desfecho
import br.com.daniel.ramos.pacientmvp.data.model.hemoglobina.Hemoglobina
import br.com.daniel.ramos.pacientmvp.data.model.idade.Idade
import br.com.daniel.ramos.pacientmvp.data.model.plaquetas.Plaqueta
import br.com.daniel.ramos.pacientmvp.data.repository.*
import kotlinx.coroutines.launch

class SplashViewModel(
    private val diabetesRepository: DiabetesRepository,
    private val leucocitoRepository: LeucocitoRepository,
    private val plaquetaRepository: PlaquetaRepository,
    private val hemoglobinaRepository: HemoglobinaRepository,
    private val idadeRepository: IdadeRepository,
    private val desfechoRepository: DesfechoRepository
) : ViewModel() {
    private var diabetesList: MutableLiveData<List<Diabete>> = MutableLiveData()
    private var leucocitoList: MutableLiveData<List<Leucocito>> = MutableLiveData()
    private var plaquetaList: MutableLiveData<List<Plaqueta>> = MutableLiveData()
    private var hemoglobinaList: MutableLiveData<List<Hemoglobina>> = MutableLiveData()
    private var idadeList: MutableLiveData<List<Idade>> = MutableLiveData()
    private var desfechoList: MutableLiveData<List<Desfecho>> = MutableLiveData()

    fun salvarDiabetesData(): LiveData<List<Diabete>> {
        viewModelScope.launch {
            val result = diabetesRepository.getDiabetesData()
            diabetesList.postValue(result)
        }
        Log.d("TAG","RODOU DIABETES")
        return diabetesList
    }

    fun salvarLeucocitosData(): LiveData<List<Leucocito>> {
        viewModelScope.launch {
            val result = leucocitoRepository.getLeucocitoData()
            leucocitoList.postValue(result)
        }
        Log.d("TAG","RODOU LEUCOCITO")
        return leucocitoList
    }

    fun salvarPlaquetasData(): LiveData<List<Plaqueta>> {
        viewModelScope.launch {
            val result = plaquetaRepository.getPlaquetaData()
            plaquetaList.postValue(result)
        }
        Log.d("TAG","RODOU PLAQUETA")
        return plaquetaList
    }

    fun salvarHemoglobinasData(): LiveData<List<Hemoglobina>> {
        viewModelScope.launch {
            val result = hemoglobinaRepository.getHemoglobinaData()
            hemoglobinaList.postValue(result)
        }
        Log.d("TAG","RODOU HEMOGLOBINA")
        return hemoglobinaList
    }

    fun salvarIdadesData(): LiveData<List<Idade>> {
        viewModelScope.launch {
            val result = idadeRepository.getIdadeData()
            idadeList.postValue(result)
        }
        Log.d("TAG","RODOU IDADE")
        return idadeList
    }

    fun salvarDesfechosData(): LiveData<List<Desfecho>> {
        viewModelScope.launch {
            val result = desfechoRepository.getDesfechoData()
            desfechoList.postValue(result)
        }
        Log.d("TAG","RODOU DESFECHO")
        return desfechoList
    }
}