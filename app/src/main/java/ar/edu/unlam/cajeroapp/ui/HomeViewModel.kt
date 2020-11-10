package ar.edu.unlam.cajeroapp.ui
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ar.edu.unlam.cajeroapp.data.entity.CuentaEntity
import ar.edu.unlam.cajeroapp.data.room.CuentaRepository
import ar.edu.unlam.cajeroapp.data.room.UsuarioRepository
import kotlinx.android.synthetic.main.activity_home.*
import java.lang.Exception

class HomeViewModel( ) : ViewModel() {



    val estado = MutableLiveData<Int>(0)
    val estadoDeposito=MutableLiveData<EstadoDeposito>()





    fun obtenerCantidadDeDinero() : Int {

        return estado.value ?:0
    }

    fun depositar(dinero: String ) {

        if (dinero !=""){
            try {
                var total=0
                estado.value?.run {
                    total=this+dinero.toInt()
                }
                estado.value=total
                estadoDeposito.postValue(EstadoDeposito.DEPOSITO_OK)
            }catch (e:Exception){
                estadoDeposito.postValue(EstadoDeposito.ERROR)
            }

        } else{

            estadoDeposito.postValue(EstadoDeposito.STRING_VACIO)
        }
    }

    fun extraer (dinero : String){

        val estadoActual=obtenerCantidadDeDinero()
        if (dinero !=""){
            try {
                if (dinero.toInt()<estadoActual) {
                    var total = 0
                    estado.value?.run {
                        total = this - dinero.toInt()
                    }
                    estado.value = total
                    estadoDeposito.postValue(EstadoDeposito.EXTRACCION_OK)
                }else{
                    estadoDeposito.postValue(EstadoDeposito.ERROR)
                }
            }catch (e:Exception){
                estadoDeposito.postValue(EstadoDeposito.ERROR)
            }

        } else{

            estadoDeposito.postValue(EstadoDeposito.STRING_VACIO)
        }



        }


    public enum class EstadoDeposito(){
       DEPOSITO_OK,
        STRING_VACIO,
        ERROR,
        EXTRACCION_OK

    }


    }





