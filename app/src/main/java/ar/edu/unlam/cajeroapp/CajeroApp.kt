package ar.edu.unlam.cajeroapp

import android.app.Application
import ar.edu.unlam.cajeroapp.ui.HomeViewModel
import ar.edu.unlam.cajeroapp.ui.UsuarioViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class CajeroApp : Application() {

    val appModule= module {

        viewModel { HomeViewModel() }
        viewModel { UsuarioViewModel(get()) }
    }
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@CajeroApp)
            modules(appModule)
        }

    }
    }