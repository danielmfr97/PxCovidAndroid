package br.com.daniel.ramos.pacientmvp.di

import br.com.daniel.ramos.pacientmvp.data.repository.*
import br.com.daniel.ramos.pacientmvp.view.avaliacoes.AvaliacoesViewModel
import br.com.daniel.ramos.pacientmvp.view.metodologia.MetodologiaViewModel
import br.com.daniel.ramos.pacientmvp.view.splash.SplashViewModel
import br.com.daniel.ramos.pacientmvp.view.trabalhos.TrabalhosViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModule {
    val module = module {
        factory { DiabetesRepository() }
        factory { LeucocitoRepository() }
        factory { PlaquetaRepository() }
        factory { HemoglobinaRepository() }
        factory { IdadeRepository() }
        factory { DesfechoRepository() }
        factory { TrabalhoRepository() }
        factory { PacienteRepository() }
        factory { MetodologiaRepository() }
        viewModel {
            SplashViewModel(
                diabetesRepository = get(),
                leucocitoRepository = get(),
                plaquetaRepository = get(),
                hemoglobinaRepository = get(),
                idadeRepository = get(),
                desfechoRepository = get()
            )
        }
        viewModel {
            AvaliacoesViewModel(get(), get(), get(), get(), get(), get(), get())
        }

        viewModel {
            TrabalhosViewModel(get())
        }

        viewModel {
            MetodologiaViewModel(get())
        }

    }

}