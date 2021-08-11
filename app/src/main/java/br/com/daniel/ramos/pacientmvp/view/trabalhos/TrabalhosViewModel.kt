package br.com.daniel.ramos.pacientmvp.view.trabalhos

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.daniel.ramos.pacientmvp.data.model.trabalho.Trabalho
import br.com.daniel.ramos.pacientmvp.data.repository.TrabalhoRepository
import io.realm.RealmResults

class TrabalhosViewModel(private val trabalhoRepository: TrabalhoRepository) : ViewModel() {
    // Dados populados na primeira vez que a aplicação iniciar
    val trabalhosList: RealmResults<Trabalho> = trabalhoRepository.getTrabalhos()
}