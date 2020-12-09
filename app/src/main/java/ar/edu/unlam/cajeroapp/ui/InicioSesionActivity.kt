package ar.edu.unlam.cajeroapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import ar.edu.unlam.cajeroapp.R
import ar.edu.unlam.cajeroapp.databinding.ActivityInicioSesionBinding

import org.koin.android.viewmodel.ext.android.viewModel


class InicioSesionActivity : AppCompatActivity() {

    private val vm: MainViewModel by viewModel()

    private lateinit var binding: ActivityInicioSesionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding = ActivityInicioSesionBinding.inflate(layoutInflater)
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



        binding.ingresar.setOnClickListener() {

            vm.iniciarSesion( binding.nombreUsuario.text.toString())

        }


        binding.registrarse.setOnClickListener() {


            val intent = Intent(this, RegistrarseActivity::class.java)
            startActivity(intent)

        }
    }

    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }

}









