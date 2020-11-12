package ar.edu.unlam.cajeroapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import ar.edu.unlam.cajeroapp.R

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.notificacion
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val vm: MainViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setListeners()


        vm.estadoinicioSesion.observe(this, Observer {
            when (it) {
                MainViewModel.inicioSesion.OK -> {
                    val intent = Intent(this, HomeActivity::class.java)
                    intent.putExtra(HomeActivity.USER_ID_PARAM, vm.usuario.value!!.id)
                    startActivity(intent)
                }
                MainViewModel.inicioSesion.ERROR -> notificacion.text =
                    getString(R.string.usuario_no)
            }
        })


    }


    private fun setListeners() {

        ingresar.setOnClickListener() {

            vm.iniciarSesion(nombreUsuario.text.toString())

        }

        registrarse.setOnClickListener() {


            val intent = Intent(this, RegistrarseActivity::class.java)
            startActivity(intent)

        }
    }


}









