package ar.edu.unlam.cajeroapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.cajeroapp.data.entity.CuentaEntity
import ar.edu.unlam.cajeroapp.data.entity.UsuarioEntity
import ar.edu.unlam.cajeroapp.data.room.CuentaRepository
import ar.edu.unlam.cajeroapp.data.room.UsuarioRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class TrasnferenciaViewModel(
    private val usuarioRepository: UsuarioRepository,
    private val cuentaRepository: CuentaRepository
) : ViewModel() {


    val estadoTrasferencia = MutableLiveData<EstadoTransferencia>()
    val cuenta = MutableLiveData<CuentaEntity>()
    val usuario = MutableLiveData<UsuarioEntity>()
    val ultUsuarioTrasferido = MutableLiveData<String>()
    val ultMontoTransferido = MutableLiveData<String>()


    fun buscarCuentaPorIdDeUsuario(id: Long) {
        viewModelScope.launch {
            cuenta.value = cuentaRepository.searchAccount(id)
            usuario.value = usuarioRepository.getById(id)
        }

    }

    fun transferir(monto: String, nombreUsuarioATransferir: String) {
        if (monto != "") {
            try {
                if (monto.toInt() < cuenta.value?.dinero ?: 0) {
                    viewModelScope.launch {

                        usuarioRepository.getAll()
                            .find { it.nombre == nombreUsuarioATransferir }
                            .let {
                                if (it == null) {
                                    estadoTrasferencia.postValue(EstadoTransferencia.USUARIONOENCONTRADO)
                                } else {
                                    val cuentaExtraccion = CuentaEntity(
                                        id = cuenta.value!!.id,
                                        dinero = (cuenta.value!!.dinero - monto.toInt()),
                                        idUsuario = cuenta.value!!.idUsuario
                                    )


                                    cuentaRepository.update(cuentaExtraccion)
                                    val cuentaATrasferir = cuentaRepository.searchAccount(
                                        usuarioRepository.getByName(nombreUsuarioATransferir).id
                                    )


                                    val cuentaDeposito = CuentaEntity(
                                        id = cuentaATrasferir!!.id,
                                        dinero = cuentaATrasferir.dinero + monto.toInt(),
                                        idUsuario = cuentaATrasferir.idUsuario
                                    )


                                    cuentaRepository.update(cuentaDeposito)

                                    estadoTrasferencia.postValue(EstadoTransferencia.OK)
                                    ultMontoTransferido.postValue(monto)
                                    ultUsuarioTrasferido.postValue(nombreUsuarioATransferir)
                                }
                            }

                    }
                } else {
                    estadoTrasferencia.postValue(EstadoTransferencia.DINEROINSUFICIENTE)
                }
            } catch (e: Exception) {
                estadoTrasferencia.postValue(EstadoTransferencia.ERROR)
            }
        } else {
            estadoTrasferencia.postValue(EstadoTransferencia.ERROR)
        }
    }

    enum class EstadoTransferencia {
        OK,
        ERROR,
        DINEROINSUFICIENTE,
        USUARIONOENCONTRADO
    }

}