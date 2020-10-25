package ar.edu.unlam.cajeroapp.ui

import kotlinx.android.synthetic.main.activity_home.*


import DatabaseViewModelFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import ar.edu.unlam.cajeroapp.R

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val usuarioViewModel: UsuarioViewModel by viewModels {
            DatabaseViewModelFactory(
                applicationContext
            )
        }


        val miViewModel: HomeViewModel by viewModels()


        miViewModel.estado.observe(this, Observer { dineroEnCuenta.text=it.toString() })
        miViewModel.estadoDeposito.observe(this, Observer {
            when(it){

                HomeViewModel.EstadoDeposito.DEPOSITO_OK -> {
                    dineroADepositar.setText("")
                notificacion.text=getString(R.string.dinero_ok)
                }

                HomeViewModel.EstadoDeposito.STRING_VACIO ->notificacion.text=getString(R.string.suma_invalida)

                HomeViewModel.EstadoDeposito.ERROR ->notificacion.text=getString(R.string.suma_invalida)

                HomeViewModel.EstadoDeposito.EXTRACCION_OK -> {
                    dineroADepositar.setText("")
                    notificacion.text=getString(R.string.extraccion_ok)
                }
            }
        })



        depositar.setOnClickListener() {

                miViewModel.depositar(dineroADepositar.text.toString())

            extraer.setOnClickListener() {

                    miViewModel.extraer(dineroAExtraer.text.toString())

            }
        }

    }

}





