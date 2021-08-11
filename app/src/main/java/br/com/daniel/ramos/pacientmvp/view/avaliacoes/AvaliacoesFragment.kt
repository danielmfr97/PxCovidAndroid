package br.com.daniel.ramos.pacientavaliation.ui.avaliacoes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AutoCompleteTextView
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import br.com.daniel.ramos.pacientavaliation.data.model.diabetes.Diabete
import br.com.daniel.ramos.pacientavaliation.data.model.leucocito.Leucocito
import br.com.daniel.ramos.pacientmvp.R
import br.com.daniel.ramos.pacientmvp.data.model.hemoglobina.Hemoglobina
import br.com.daniel.ramos.pacientmvp.data.model.idade.Idade
import br.com.daniel.ramos.pacientmvp.data.model.plaquetas.Plaqueta
import br.com.daniel.ramos.pacientmvp.databinding.FragmentAvaliacoesBinding
import br.com.daniel.ramos.pacientmvp.view.adapters.CustomArrayAdapter
import br.com.daniel.ramos.pacientmvp.view.avaliacoes.AvaliacoesViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class AvaliacoesFragment : Fragment() {
    private lateinit var binding: FragmentAvaliacoesBinding
    private val viewModel: AvaliacoesViewModel by sharedViewModel()
    private var bundle: Bundle? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAvaliacoesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        setMenuDesfechoAdapter()
        setMenuDiabetesAdapter()
        setMenuHemoglobinasAdapter()
        setMenuIdadeAdapter()
        setMenuLeucocitosAdapter()
        setMenuPlaquetasAdapter()
        configurarBotaoConsulta()
    }

    private fun setMenuDiabetesAdapter() {
        val menuDiabetes = (binding.menuDiabetes.editText as? AutoCompleteTextView)!!
        registerForContextMenu(menuDiabetes)
        menuDiabetes.apply {
            setAdapter(
                CustomArrayAdapter(
                    requireContext(),
                    R.layout.support_simple_spinner_dropdown_item,
                    values = viewModel.getDiabeteList()
                )
            )
            onItemClickListener = onDiabeteSelecionado
        }
    }

    private val onDiabeteSelecionado =
        AdapterView.OnItemClickListener { parent, view, position, id ->
            val diabete: Diabete = parent?.getItemAtPosition(position) as Diabete
            (binding.menuDiabetes.editText as? AutoCompleteTextView)!!.setText(diabete.label)
            viewModel.diabeteSelected.postValue(diabete)
        }


    private fun setMenuLeucocitosAdapter() {
        val menuLeucocitos = (binding.menuLeucocitos.editText as? AutoCompleteTextView)!!
        registerForContextMenu(menuLeucocitos)
        menuLeucocitos.apply {
            setAdapter(
                CustomArrayAdapter(
                    requireContext(),
                    R.layout.support_simple_spinner_dropdown_item,
                    values = viewModel.getLeucocitoList()
                )
            )
            onItemClickListener = onLeucocitoSelecionado
        }
    }

    private val onLeucocitoSelecionado =
        AdapterView.OnItemClickListener { parent, view, position, id ->
            val leucocito = parent?.getItemAtPosition(position) as Leucocito
            (binding.menuLeucocitos.editText as? AutoCompleteTextView)!!.setText(leucocito.label)
            viewModel.leucocitoSelected.postValue(leucocito)
        }

    private fun setMenuPlaquetasAdapter() {
        val menuPlaquetas = (binding.menuPlaquetas.editText as? AutoCompleteTextView)!!
        registerForContextMenu(menuPlaquetas)
        menuPlaquetas.apply {
            setAdapter(
                CustomArrayAdapter(
                    requireContext(),
                    R.layout.support_simple_spinner_dropdown_item,
                    values = viewModel.getPlaquetaList()
                )
            )
            onItemClickListener = onPlaquetaSelecionada
        }
    }

    private val onPlaquetaSelecionada =
        AdapterView.OnItemClickListener { parent, view, position, id ->
            val plaqueta = parent?.getItemAtPosition(position) as Plaqueta
            (binding.menuPlaquetas.editText as? AutoCompleteTextView)!!.setText(plaqueta.label)
            viewModel.plaquetaSelected.postValue(plaqueta)
        }

    private fun setMenuHemoglobinasAdapter() {
        val menuHemoglobina = (binding.menuHemoglobina.editText as? AutoCompleteTextView)!!
        registerForContextMenu(menuHemoglobina)
        menuHemoglobina.apply {
            setAdapter(
                CustomArrayAdapter(
                    requireContext(),
                    R.layout.support_simple_spinner_dropdown_item,
                    values = viewModel.getHemoglobinaList()
                )
            )
            onItemClickListener = onHemoglobinaSelecionada
        }
    }

    private val onHemoglobinaSelecionada =
        AdapterView.OnItemClickListener { parent, view, position, id ->
            val hemoglobina = parent?.getItemAtPosition(position) as Hemoglobina
            (binding.menuHemoglobina.editText as? AutoCompleteTextView)!!.setText(hemoglobina.label)
            viewModel.hemoglobinaSelected.postValue(hemoglobina)
        }

    private fun setMenuIdadeAdapter() {
        val menuIdade = (binding.menuIdade.editText as? AutoCompleteTextView)!!
        registerForContextMenu(menuIdade)
        menuIdade.apply {
            setAdapter(
                CustomArrayAdapter(
                    requireContext(),
                    R.layout.support_simple_spinner_dropdown_item,
                    values = viewModel.getIdadeList()
                )
            )
            onItemClickListener = onIdadeSelecionada
        }
    }

    private val onIdadeSelecionada = AdapterView.OnItemClickListener { parent, view, position, id ->
        val idade = parent?.getItemAtPosition(position) as Idade
        (binding.menuIdade.editText as? AutoCompleteTextView)!!.setText(idade.label)
        viewModel.idadeSelected.postValue(idade)
    }
//
//    private fun setMenuDesfechoAdapter() {
//        val menuDesfecho = (binding.menuDesfecho.editText as? AutoCompleteTextView)!!
//        registerForContextMenu(menuDesfecho)
//        menuDesfecho.apply {
//            setAdapter(
//                CustomArrayAdapter(
//                    requireContext(),
//                    R.layout.support_simple_spinner_dropdown_item,
//                    values = viewModel.getDesfechoList()
//                )
//            )
//            onItemClickListener = onDesfechoSelecionado
//        }
//    }
//
//    private val onDesfechoSelecionado = AdapterView.OnItemClickListener { parent, view, position, id ->
//        val desfecho = parent?.getItemAtPosition(position) as Desfecho
//        (binding.menuDesfecho.editText as? AutoCompleteTextView)!!.setText(desfecho.label)
//    }

    private fun configurarBotaoConsulta() {
        binding.btnConsultar.setOnClickListener {
            viewModel.consultar().observe(viewLifecycleOwner, Observer {
                findNavController().navigate(R.id.action_avaliacoesFragment_to_resultadoFragment)
            })
        }
    }
}

