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


    val cuenta = MutableLiveData<CuentaEntity>()
    val estadoDeposito = MutableLiveData<EstadoDeposito>()


    fun buscarCuentaPorIdDeUsuario(id: Long) {
        viewModelScope.launch {
            cuenta.value = cuentaRepository.searchAccount(id)
        }

    }


    fun depositar(dinero: String, idUsuario: Long) {

        if (dinero != "") {
            try {
                estadoDeposito.postValue(EstadoDeposito.DEPOSITO_OK)
                viewModelScope.launch {

                    val cuentaAct = CuentaEntity(id = cuenta.value!!.id, dinero = (cuenta.value!!.dinero + dinero.toInt()), idUsuario = cuenta.value!!.idUsuario)
                    cuentaRepository.update(cuentaAct)
                    cuenta.value = cuentaAct

                }
            } catch (e: Exception) {
                estadoDeposito.postValue(EstadoDeposito.ERROR)
            }

        } else {

            estadoDeposito.postValue(EstadoDeposito.STRING_VACIO)
        }
    }

    fun extraer(dinero: String, idUsuario: Long) {

        if (dinero != "") {
            try {
                if (dinero.toInt() < cuenta.value?.dinero ?: 0) {
                    estadoDeposito.postValue(EstadoDeposito.EXTRACCION_OK)
                    viewModelScope.launch {
                        val cuentaAct = CuentaEntity(id = cuenta.value!!.id, dinero = (cuenta.value!!.dinero - dinero.toInt()), idUsuario = cuenta.value!!.idUsuario)
                        cuentaRepository.update(cuentaAct)
                        cuenta.value = cuentaAct
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





