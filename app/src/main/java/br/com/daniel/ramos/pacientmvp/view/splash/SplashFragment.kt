package br.com.daniel.ramos.pacientmvp.view.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import androidx.navigation.fragment.findNavController
import br.com.daniel.ramos.pacientmvp.R
import org.koin.androidx.viewmodel.ext.android.viewModel
import br.com.daniel.ramos.pacientmvp.databinding.FragmentSplashBinding
import br.com.daniel.ramos.pacientmvp.view.home.HomeFragmentDirections
import kotlinx.coroutines.launch

class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding
    private val viewModel: SplashViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            // Rodará apenas quando o fragment for criado
            whenStarted {
                downloadData()
            }
            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
        }
    }

    private fun downloadData() {
        viewModel.salvarDiabetesData()
        viewModel.salvarLeucocitosData()
        viewModel.salvarPlaquetasData()
        viewModel.salvarHemoglobinasData()
        viewModel.salvarIdadesData()
        viewModel.salvarDesfechosData()
    }

}