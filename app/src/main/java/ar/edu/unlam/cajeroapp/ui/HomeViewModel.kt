package ar.edu.unlam.cajeroapp.ui
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.android.synthetic.main.activity_home.*

class HomeViewModel() : ViewModel() {

    val estado = MutableLiveData<Int>()




    fun obtenerCantidadDeDinero() : Int {

        return estado.value ?:0
    }

    fun depositar(dinero : Int ) {

        val valorActual = obtenerCantidadDeDinero()

            estado.value = valorActual + dinero
        }

    fun extraer (dinero : Int){

        val valorActual = obtenerCantidadDeDinero()

            if (dinero <= valorActual)
                estado.value = valorActual - dinero

        }

    }





