package br.com.daniel.ramos.pacientmvp.view.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import br.com.daniel.ramos.pacientavaliation.ui.home.HomeViewModel
import br.com.daniel.ramos.pacientmvp.R
import br.com.daniel.ramos.pacientmvp.databinding.FragmentHomeBinding


class HomeFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        configurarBotoes()
    }

    private fun configurarBotoes() {
        binding.tvAddPaciente.setOnClickListener(this)
        binding.tvAvaliacaoPaciente.setOnClickListener(this)
        binding.tvInfoMetodologia.setOnClickListener(this)
        binding.tvTrabalhosAcademicos.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id) {
            binding.tvAvaliacaoPaciente.id -> { findNavController().navigate(R.id.action_homeFragment_to_avaliacoesFragment)}
            binding.tvAddPaciente.id -> { findNavController().navigate(R.id.action_homeFragment_to_pacienteFragment) }
            binding.tvInfoMetodologia.id -> { findNavController().navigate(R.id.action_homeFragment_to_metodologiaFragment) }
            binding.tvTrabalhosAcademicos.id -> { findNavController().navigate(R.id.action_homeFragment_to_trabalhosFragment) }
        }
    }
}