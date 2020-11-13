package ar.edu.unlam.cajeroapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.cajeroapp.data.room.CuentaRepository
import ar.edu.unlam.cajeroapp.model.Usuario
import ar.edu.unlam.cajeroapp.data.room.UsuarioRepository
import ar.edu.unlam.cajeroapp.model.Cuenta
import kotlinx.coroutines.launch


class RegistrarseViewModel(
    private val usuarioRepository: UsuarioRepository,
    private val cuentaRepository: CuentaRepository
) : ViewModel() {


    fun save(usuario: Usuario) {

        viewModelScope.launch {
            usuarioRepository.save(usuario)
            val nuevaCuenta = Cuenta(0, usuarioRepository.getByName(usuario.nombre).id )
            cuentaRepository.save(nuevaCuenta)

        }

    }


}












