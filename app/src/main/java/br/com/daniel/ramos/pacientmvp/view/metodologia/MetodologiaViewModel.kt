package br.com.daniel.ramos.pacientmvp.view.metodologia

import androidx.lifecycle.ViewModel
import br.com.daniel.ramos.pacientmvp.data.model.Info
import br.com.daniel.ramos.pacientmvp.data.repository.MetodologiaRepository

class MetodologiaViewModel(private val metodologiaRepository: MetodologiaRepository) : ViewModel() {
    val inteligenciaInfo: Info = metodologiaRepository.getIA()
    val algoritmoInfo: Info = metodologiaRepository.getAlgoritmo()
    val contribuirInfo: Info = metodologiaRepository.getContribuir()
    val metricasInfo: Info = metodologiaRepository.getMetricas()
    val projetoInfo: Info = metodologiaRepository.getProjeto()

}