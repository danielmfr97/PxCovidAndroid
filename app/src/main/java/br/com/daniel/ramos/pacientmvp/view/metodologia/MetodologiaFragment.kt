package br.com.daniel.ramos.pacientmvp.view.metodologia

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import br.com.daniel.ramos.pacientmvp.R
import br.com.daniel.ramos.pacientmvp.databinding.FragmentMetodologiaBinding

class MetodologiaFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentMetodologiaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMetodologiaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configurarBotoes()
    }

    private fun configurarBotoes() {
        binding.btnInteligenciaArtificial.setOnClickListener (this)
        binding.btnAlgoritmo.setOnClickListener (this)
        binding.btnContribuir.setOnClickListener (this)
        binding.btnMetricas.setOnClickListener (this)
        binding.btnProjeto.setOnClickListener (this)
    }

    override fun onClick(v: View) {
        val bundle = Bundle()
        when(v.id) {
            binding.btnInteligenciaArtificial.id -> bundle.putString("tipo", "inteligencia")
            binding.btnAlgoritmo.id -> bundle.putString("tipo", "algoritmo")
            binding.btnContribuir.id -> bundle.putString("tipo", "contribuir")
            binding.btnMetricas.id -> bundle.putString("tipo", "metricas")
            binding.btnProjeto.id -> bundle.putString("tipo", "projeto")
        }
        findNavController().navigate(R.id.action_metodologiaFragment_to_customInfoFragment, bundle)
    }

}