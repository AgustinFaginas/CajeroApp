package ar.edu.unlam.cajeroapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.cajeroapp.data.entity.CuentaEntity
import ar.edu.unlam.cajeroapp.data.room.CuentaRepository
import ar.edu.unlam.cajeroapp.data.room.UsuarioRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeViewModel(
    private val cuentaRepository: CuentaRepository,
    private val usuarioRepository: UsuarioRepository
) : ViewModel() {


    val estado = MutableLiveData<Int>(0)
    val cuenta = MutableLiveData<CuentaEntity>()
    val estadoDeposito = MutableLiveData<EstadoDeposito>()


    fun buscarCuentaPorIdDeUsuario(id: Long) {
        viewModelScope.launch {

            val cuentaBuscada = cuentaRepository.searchAccount(id)
            cuenta.value = cuentaBuscada

            var total = 0
            estado.value?.run {
                total = cuenta.value!!.dinero
            }
            estado.value = total

        }

    }


    fun depositar(dinero: String, idUsuario: Long) {

        if (dinero != "") {
            try {
                var total = dinero.toInt() + (cuenta.value?.dinero ?: 0)
                estado.value = total
                estadoDeposito.postValue(EstadoDeposito.DEPOSITO_OK)
                viewModelScope.launch {
                    if (total != null) {
                        cuentaRepository.depositar(idUsuario, total)
                    }
                }
            } catch (e: Exception) {
                estadoDeposito.postValue(EstadoDeposito.ERROR)
            }

        } else {

            estadoDeposito.postValue(EstadoDeposito.STRING_VACIO)
        }
    }

    fun extraer(dinero: String, idUsuario: Long) {

        val estadoActual = estado.value
        if (dinero != "") {
            try {
                if (dinero.toInt() < estadoActual!!) {
                    var total = 0
                    estado.value?.run {
                        total = this - dinero.toInt()
                    }
                    estado.value = total
                    estadoDeposito.postValue(EstadoDeposito.EXTRACCION_OK)
                    viewModelScope.launch {
                        cuentaRepository.extraer(idUsuario, dinero.toInt())
                    }

                } else {
                    estadoDeposito.postValue(EstadoDeposito.ERROR)
                }
            } catch (e: Exception) {
                estadoDeposito.postValue(EstadoDeposito.ERROR)
            }

        } else {

            estadoDeposito.postValue(EstadoDeposito.STRING_VACIO)
        }


    }


    public enum class EstadoDeposito() {
        DEPOSITO_OK,
        STRING_VACIO,
        ERROR,
        EXTRACCION_OK

    }


}





