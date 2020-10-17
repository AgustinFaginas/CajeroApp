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

        val usuarioViewModel : UsuarioViewModel by viewModels{DatabaseViewModelFactory(applicationContext)}

        val miViewModel: MainViewModel by viewModels ()


        dineroEnCuenta.setText("$ ${miViewModel.obtenerCantidadDeDinero()}")



        depositar.setOnClickListener(){

            if (!dineroADepositar.getText().toString().trim().equals("")){

                miViewModel.depositar(dineroADepositar.text.toString().toInt())
                dineroEnCuenta.setText("$ ${miViewModel.obtenerCantidadDeDinero()}")
                dineroADepositar.setText("")
                notificacion.setText("Dinero depositado correctamente ")
            } else
                notificacion.setText("El campo depositar debe contener una suma de dinero")




        }
        extraer.setOnClickListener(){
            if (!dineroAExtraer.getText().toString().trim().equals("")){

                miViewModel.extraer(dineroAExtraer.text.toString().toInt())
                dineroEnCuenta.setText("$ ${miViewModel.obtenerCantidadDeDinero()}")
                dineroAExtraer.setText("")
                notificacion.setText("Dinero extraido correctamente")
            } else
                notificacion.setText("El campo extraer debe contener una suma de dinero valida")


        }

    }
}
