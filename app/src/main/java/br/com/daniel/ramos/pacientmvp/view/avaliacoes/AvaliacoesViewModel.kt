package br.com.daniel.ramos.pacientmvp.view.avaliacoes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.daniel.ramos.pacientavaliation.data.model.diabetes.Diabete
import br.com.daniel.ramos.pacientavaliation.data.model.leucocito.Leucocito
import br.com.daniel.ramos.pacientmvp.data.model.desfecho.Desfecho
import br.com.daniel.ramos.pacientmvp.data.model.hemoglobina.Hemoglobina
import br.com.daniel.ramos.pacientmvp.data.model.idade.Idade
import br.com.daniel.ramos.pacientmvp.data.model.paciente.Paciente
import br.com.daniel.ramos.pacientmvp.data.model.plaquetas.Plaqueta
import br.com.daniel.ramos.pacientmvp.data.repository.*
import kotlinx.coroutines.launch
import org.json.JSONObject

class AvaliacoesViewModel(
    private val diabetesRepository: DiabetesRepository,
    private val leucocitoRepository: LeucocitoRepository,
    private val plaquetaRepository: PlaquetaRepository,
    private val hemoglobinaRepository: HemoglobinaRepository,
    private val idadeRepository: IdadeRepository,
    private val desfechoRepository: DesfechoRepository,
    private val pacienteRepository: PacienteRepository
) : ViewModel() {

    val diabeteSelected = MutableLiveData<Diabete>()
    val leucocitoSelected = MutableLiveData<Leucocito>()
    val plaquetaSelected = MutableLiveData<Plaqueta>()
    val hemoglobinaSelected = MutableLiveData<Hemoglobina>()
    val idadeSelected = MutableLiveData<Idade>()
    val pacienteDiagnostico = MutableLiveData<Paciente>()


    fun getDiabeteList(): List<Diabete> {
        return diabetesRepository.getDataSavedOnDB()
    }

    fun getLeucocitoList(): List<Leucocito> {
        return leucocitoRepository.getDataSavedOnDB()
    }

    fun getPlaquetaList(): List<Plaqueta> {
        return plaquetaRepository.getDataSavedOnDB()
    }

    fun getHemoglobinaList(): List<Hemoglobina> {
        return hemoglobinaRepository.getDataSavedOnDB()
    }

    fun getIdadeList(): List<Idade> {
        return idadeRepository.getDataSavedOnDB()
    }

    fun getDesfechoList(): List<Desfecho> {
        return desfechoRepository.getDataSavedOnDB()
    }

    fun consultar(): LiveData<Paciente> {
        viewModelScope.launch {
            val result = pacienteRepository.consultarPaciente(
                diabeteSelected.value?.id!!,
                leucocitoSelected.value?.id!!,
                plaquetaSelected.value?.id!!,
                hemoglobinaSelected.value?.id!!,
                idadeSelected.value?.id!!
            )
            if (result != null)
                pacienteDiagnostico.value = result!!
        }
        return pacienteDiagnostico
    }

    fun salvarPaciente() {
        val jsonObject = JSONObject()
        jsonObject.apply {
            put("diabetes", diabeteSelected.value?.id)
            put("leucocito", leucocitoSelected.value?.id)
            put("plaquetas", plaquetaSelected.value?.id)
            put("hemoglobina", hemoglobinaSelected.value?.id)
            put("idade", idadeSelected.value?.id)
        }
        viewModelScope.launch {
            pacienteRepository.salvarPaciente(jsonObject)
        }
    }

//    fun consultar(): LiveData<PacienteDiagnostico> {
//        viewModelScope.launch {
//            val result = pacienteRepository.consultarPaciente()
//            if (result != null)
//                pacienteDiagnostico.value = result!!
//        }
//        return pacienteDiagnostico
//    }

}