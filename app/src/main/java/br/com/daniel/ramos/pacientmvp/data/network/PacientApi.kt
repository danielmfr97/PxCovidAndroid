package br.com.daniel.ramos.pacientmvp.data.network

import br.com.daniel.ramos.pacientavaliation.data.model.diabetes.DiabeteResponse
import br.com.daniel.ramos.pacientmvp.data.model.desfecho.DesfechoResponse
import br.com.daniel.ramos.pacientmvp.data.model.hemoglobina.HemoglobinaResponse
import br.com.daniel.ramos.pacientmvp.data.model.idade.IdadeResponse
import br.com.daniel.ramos.pacientmvp.data.model.leucocito.LeucocitoResponse
import br.com.daniel.ramos.pacientmvp.data.model.paciente.Paciente
import br.com.daniel.ramos.pacientmvp.data.model.plaquetas.PlaquetaResponse
import retrofit2.Response
import retrofit2.http.*

interface PacientApi {
    @GET("data/variable/diabetes")
    suspend fun getDiabetesData(): Response<DiabeteResponse>

    @GET("data/variable/leucocito")
    suspend fun getLeucocitoData(): Response<LeucocitoResponse>

    @GET("data/variable/plaquetas")
    suspend fun getPlaquetaData(): Response<PlaquetaResponse>

    @GET("data/variable/hemoglobina")
    suspend fun getHemoglobinaData(): Response<HemoglobinaResponse>

    @GET("data/variable/idade")
    suspend fun getIdadeData(): Response<IdadeResponse>

    @GET("data/variable/desfecho")
    suspend fun getDesfechoData(): Response<DesfechoResponse>

    @GET("naivebayes/paciente")
    suspend fun consultarPaciente(
        @Query(value = "diabetes") diabetes: Int,
        @Query(value = "leucocito") leucocito: Int,
        @Query(value = "plaquetas") plaquetas: Int,
        @Query(value = "hemoglobina") hemoglobina: Int,
        @Query(value = "idade") idade: Int
    ): Response<Paciente>

    @Headers("Content-Type: application/json")
    @PUT("data/paciente")
    suspend fun salvarPaciente(@Body body: String)

}