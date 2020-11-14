package ar.edu.unlam.cajeroapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.cajeroapp.data.entity.UsuarioEntity
import ar.edu.unlam.cajeroapp.data.room.CuentaRepository
import ar.edu.unlam.cajeroapp.data.room.UsuarioRepository
import ar.edu.unlam.cajeroapp.model.Cuenta
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel(private val usuarioRepository: UsuarioRepository,
                    private val cuentaRepository: CuentaRepository
) : ViewModel() {

    val estadoinicioSesion = MutableLiveData<inicioSesion>()
    val usuario = MutableLiveData<UsuarioEntity>()

    fun iniciarSesion(username: String) {
        viewModelScope.launch {
            try {

                val user = usuarioRepository.getByName(username)
                usuario.value = user
                if (user != null) {
                    estadoinicioSesion.value = inicioSesion.OK
                } else {
                    estadoinicioSesion.value = inicioSesion.ERROR
                }

            }catch (e : Exception){
                estadoinicioSesion.postValue(inicioSesion.ERROR)
            }

        }
    }



    enum class inicioSesion {
        OK,
        ERROR
    }
}