package br.com.daniel.ramos.pacientmvp.data.repository

import br.com.daniel.ramos.pacientmvp.data.model.paciente.Paciente
import br.com.daniel.ramos.pacientmvp.data.network.PacientService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

class PacienteRepository {

    suspend fun consultarPaciente(
        idDiabetes: Int,
        idLeucocito: Int,
        idPlaquetas: Int,
        idHemoglobina: Int,
        idIdade: Int
    ): Paciente? {
        return withContext(Dispatchers.IO) {
            val response = PacientService.service.consultarPaciente(
                idDiabetes,
                idLeucocito,
                idPlaquetas,
                idHemoglobina,
                idIdade
            )
            response.body()
        }
    }

    suspend fun salvarPaciente(jsonObject: JSONObject) {
        PacientService.service.salvarPaciente(jsonObject.toString())
    }

//    suspend fun consultarPaciente(): PacienteDiagnostico? {
//        return withContext(Dispatchers.IO) {
//            val response = PacientService.service.consultarPacienteDebug()
//            Log.i("TAG", "TAG")
//            response.body()?.pacientesDiagnostico
//        }
//    }
}