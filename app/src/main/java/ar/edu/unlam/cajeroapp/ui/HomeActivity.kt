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


        miViewModel.estado.observe(this, Observer {onTextChange(it)})



        depositar.setOnClickListener() {

                miViewModel.depositar(dineroADepositar.text.toString().toInt())

            extraer.setOnClickListener() {

                    miViewModel.extraer(dineroAExtraer.text.toString().toInt())

            }
        }

    }
    private fun onTextChange(text:Int){
        dineroEnCuenta.text=text.toString()

    }
}





