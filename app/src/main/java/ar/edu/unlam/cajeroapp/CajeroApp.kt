package ar.edu.unlam.cajeroapp

import android.app.Application
import android.content.Context
import androidx.room.Room
import ar.edu.unlam.cajeroapp.data.impl.RoomCuentaRepository
import ar.edu.unlam.cajeroapp.data.impl.RoomUsuarioRepository
import ar.edu.unlam.cajeroapp.data.room.CajeroDatabase
import ar.edu.unlam.cajeroapp.data.room.CuentaRepository
import ar.edu.unlam.cajeroapp.data.room.UsuarioRepository
import ar.edu.unlam.cajeroapp.ui.HomeViewModel
import ar.edu.unlam.cajeroapp.ui.UsuarioViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class CajeroApp : Application() {

    val appModule= module {


        single { provideDatabase(get()).cuentaDato()}
        single { provideDatabase(get()).usuarioDao()}
        single <UsuarioRepository>{RoomUsuarioRepository(get())}
        single <CuentaRepository> {RoomCuentaRepository(get())}
        viewModel { HomeViewModel(get()) }
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
    fun provideDatabase(context :Context): CajeroDatabase{
        return  Room.databaseBuilder(context,CajeroDatabase::class.java, "cajero_db").build()
    }
    }