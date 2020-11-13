package ar.edu.unlam.cajeroapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.cajeroapp.data.entity.CuentaEntity
import ar.edu.unlam.cajeroapp.data.room.CuentaRepository
import ar.edu.unlam.cajeroapp.data.room.UsuarioRepository
import kotlinx.coroutines.launch

class TrasnferenciaViewModel(
    private val usuarioRepository: UsuarioRepository,
    private val cuentaRepository: CuentaRepository
) : ViewModel() {


    val cuenta = MutableLiveData<CuentaEntity>()


    fun buscarCuentaPorIdDeUsuario(id: Long) {
        viewModelScope.launch {
            cuenta.value = cuentaRepository.searchAccount(id)
        }

    }

    fun transferir(monto: String, nombreUsuarioATransferir: String) {
        if (monto != "") {
            if (monto.toInt() < cuenta.value?.dinero ?: 0) {
                viewModelScope.launch {
                    val cuentaExtraccion = CuentaEntity(
                        id = cuenta.value!!.id,
                        dinero = (cuenta.value!!.dinero - monto.toInt()),
                        idUsuario = cuenta.value!!.idUsuario
                    )
                    cuentaRepository.update(cuentaExtraccion)
                    val usuarioATrasferir =
                        usuarioRepository.getByName(nombreUsuarioATransferir)
                    val cuentaATrasferir = cuentaRepository.searchAccount(usuarioATrasferir.id)
                    val cuentaDeposito = CuentaEntity(
                        id = cuentaATrasferir!!.id,
                        dinero = cuentaATrasferir.dinero + monto.toInt(),
                        idUsuario = cuentaATrasferir.idUsuario
                    )
                    cuentaRepository.update(cuentaDeposito)

                }

            }

        }
    }
}