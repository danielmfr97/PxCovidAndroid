package br.com.daniel.ramos.pacientmvp.view.trabalhos

import android.os.Bundle
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.daniel.ramos.pacientmvp.databinding.FragmentTrabalhosBinding
import br.com.daniel.ramos.pacientmvp.view.adapters.TrabalhosAdapter

class TrabalhosFragment : Fragment() {
    private var _binding: FragmentTrabalhosBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TrabalhosViewModel by viewModel()
    lateinit var mAdapter: TrabalhosAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTrabalhosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        mAdapter = TrabalhosAdapter()
        mAdapter.trabalhosList = viewModel.trabalhosList
        binding.rvTrabalhos.adapter = mAdapter
    }

}