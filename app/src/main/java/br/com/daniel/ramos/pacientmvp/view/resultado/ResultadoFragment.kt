package br.com.daniel.ramos.pacientmvp.view.resultado

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import br.com.daniel.ramos.pacientmvp.R
import br.com.daniel.ramos.pacientmvp.databinding.FragmentResultadoBinding
import br.com.daniel.ramos.pacientmvp.view.avaliacoes.AvaliacoesViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ResultadoFragment : Fragment() {
    private lateinit var binding: FragmentResultadoBinding
    private val viewModel: AvaliacoesViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResultadoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configurarBotoes()
        popularDados()
    }

    private fun configurarBotoes() {
        binding.btnConsulta.setOnClickListener {
            findNavController().navigate(ResultadoFragmentDirections.actionResultadoFragmentToAvaliacoesFragment())
        }
        binding.btnInfoMetodologia.setOnClickListener {
            findNavController().navigate(ResultadoFragmentDirections.actionResultadoFragmentToMetodologiaFragment())
        }
        binding.btnTrabalhosAcademicos.setOnClickListener {
            findNavController().navigate(ResultadoFragmentDirections.actionResultadoFragmentToTrabalhosFragment())
        }
    }

    private fun popularDados() {
        val valor = "${viewModel.pacienteDiagnostico.value?.value?.times(100)}%"
        val diagnostico = viewModel.pacienteDiagnostico.value?.diagnostic
        val level = viewModel.pacienteDiagnostico.value?.level
        binding.tvDiagnostico.text = getString(R.string.probabilidade_diagnostico, diagnostico)
        binding.tvPorcentagem.text = valor
        binding.tvDescription.text = getString(R.string.description, valor, level)
        binding.resDiabetes.text = viewModel.diabeteSelected.value?.displayName
        binding.resHemoglobina.text = viewModel.hemoglobinaSelected.value?.displayName
        binding.resIdade.text = viewModel.idadeSelected.value?.displayName
        binding.resLeucocitos.text = viewModel.leucocitoSelected.value?.displayName
        binding.resPlaquetas.text = viewModel.plaquetaSelected.value?.displayName
    }

}