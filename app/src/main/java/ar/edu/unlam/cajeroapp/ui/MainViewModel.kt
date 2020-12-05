package ar.edu.unlam.cajeroapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.cajeroapp.data.entity.UsuarioEntity
import ar.edu.unlam.cajeroapp.data.room.CuentaRepository
import ar.edu.unlam.cajeroapp.data.room.UsuarioRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel(
    private val usuarioRepository: UsuarioRepository,
    private val cuentaRepository: CuentaRepository
) : ViewModel() {

    val estadoinicioSesion = MutableLiveData<estadoInicioSesion>()
    val usuario = MutableLiveData<UsuarioEntity>()

    fun iniciarSesion(username: String) {
        viewModelScope.launch {
            try {

                val user = usuarioRepository.getByName(username)
                usuario.value = user
                if (user != null) {
                    estadoinicioSesion.value = estadoInicioSesion.OK
                } else {
                    estadoinicioSesion.value = estadoInicioSesion.ERROR
                }

            } catch (e: Exception) {
                estadoinicioSesion.postValue(estadoInicioSesion.ERROR)
            }

        }
    }


    enum class estadoInicioSesion {
        OK,
        ERROR
    }
}