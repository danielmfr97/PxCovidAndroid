package br.com.daniel.ramos.pacientmvp.view.splash

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import androidx.navigation.fragment.findNavController
import br.com.daniel.ramos.pacientmvp.databinding.FragmentSplashBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding
    private val viewModel: SplashViewModel by viewModel()
    val counter = 6
    var temp = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        GlobalScope.launch(Dispatchers.Main) {
            //TODO: Não funciona asincronamente, corrigir para melhor tempo de execução
            val download = async { downloadData() }
        }
    }

    private fun downloadData() {
        viewModel.salvarDiabetesData().observe(viewLifecycleOwner, Observer {
            if (it.size > 0) {
                temp++
                check()
            }
        })
        viewModel.salvarLeucocitosData().observe(viewLifecycleOwner, Observer {
            if (it.size > 0) {
                temp++
                check()
            }
        })
        viewModel.salvarPlaquetasData().observe(viewLifecycleOwner, Observer {
            if (it.size > 0) {
                temp++
                check()
            }
        })
        viewModel.salvarHemoglobinasData().observe(viewLifecycleOwner, Observer {
            if (it.size > 0) {
                temp++
                check()
            }
        })
        viewModel.salvarIdadesData().observe(viewLifecycleOwner, Observer {
            if (it.size > 0) {
                temp++
                check()
            }
        })
        viewModel.salvarDesfechosData().observe(viewLifecycleOwner, Observer {
            if (it.size > 0) {
                temp++
                check()
            }
        })


    }

    private fun check() {
        if (temp == counter)
            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
    }

}