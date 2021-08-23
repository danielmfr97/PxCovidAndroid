package br.com.daniel.ramos.pacientmvp.view.metodologia

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.daniel.ramos.pacientmvp.MainActivity
import br.com.daniel.ramos.pacientmvp.data.model.Info
import br.com.daniel.ramos.pacientmvp.databinding.FragmentCustomInfoBinding
import br.com.daniel.ramos.pacientmvp.view.adapters.ReferenciaAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class CustomInfoFragment : Fragment() {
    private var _binding: FragmentCustomInfoBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MetodologiaViewModel by viewModel()
    private lateinit var mAdapter: ReferenciaAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCustomInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        when (arguments?.getString("tipo")) {
            "inteligencia" -> {
                popularDados(viewModel.inteligenciaInfo)
                setToolbarTitle("Inteligência Artificial")
            }
            "algoritmo" -> {
                popularDados(viewModel.algoritmoInfo)
                setToolbarTitle("Algoritmo Utilizado")
            }
            "metricas" -> {
                popularDados(viewModel.contribuirInfo)
                setToolbarTitle("Métricas")
            }
            "projeto" -> {
                popularDados(viewModel.metricasInfo)
                setToolbarTitle("Sobre o Projeto")
            }
            "contribuir" -> {
                popularDados(viewModel.projetoInfo)
                setToolbarTitle("Formas de Contribuir")
            }
        }
    }

    private fun popularDados(info: Info) {
        mAdapter = ReferenciaAdapter()
        mAdapter.infoList = info.referencias
        val text = "<html><body><p align=\"justify\">${info.resumo}</p></body></html>"
        binding.tvExplicacao.loadData(text, "text/html", "utf-8")
        if (mAdapter.infoList.isEmpty())
            binding.labelRef.visibility = View.GONE
        binding.rvReferencias.adapter = mAdapter
    }

    private fun setToolbarTitle(title: String) {
        (requireActivity() as MainActivity).title = title
    }
}