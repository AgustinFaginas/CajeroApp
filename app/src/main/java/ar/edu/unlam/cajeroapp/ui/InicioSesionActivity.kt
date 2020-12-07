package ar.edu.unlam.cajeroapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import ar.edu.unlam.cajeroapp.R
import ar.edu.unlam.cajeroapp.databinding.ActivityInicioSesionBinding
import ar.edu.unlam.cajeroapp.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_inicio_sesion.*
import kotlinx.android.synthetic.main.activity_main.*

import org.koin.android.viewmodel.ext.android.viewModel


class InicioSesionActivity : AppCompatActivity() {

    private val vm: MainViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityInicioSesionBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setListeners()


        vm.estadoinicioSesion.observe(this, Observer {
            when (it) {
                MainViewModel.estadoInicioSesion.OK -> {
                    val intent = Intent(this, HomeActivity::class.java)
                    intent.putExtra(HomeActivity.USER_ID_PARAM, vm.usuario.value!!.id)
                    startActivity(intent)
                }
                MainViewModel.estadoInicioSesion.ERROR ->binding.notificacion.text =
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

    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }

}








