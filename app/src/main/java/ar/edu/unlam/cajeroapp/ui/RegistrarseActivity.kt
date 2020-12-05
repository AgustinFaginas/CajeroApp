package ar.edu.unlam.cajeroapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import ar.edu.unlam.cajeroapp.R
import ar.edu.unlam.cajeroapp.databinding.ActivityRegistrarseBinding
import ar.edu.unlam.cajeroapp.model.Usuario
import kotlinx.android.synthetic.main.activity_registrarse.*

import org.koin.android.viewmodel.ext.android.viewModel

class RegistrarseActivity : AppCompatActivity() {

    private val registrarseViewModel: RegistrarseViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityRegistrarseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListeners()

        registrarseViewModel.estadoRegistracion.observe(this, Observer {
            when (it) {
                RegistrarseViewModel.EstadoRegistro.OK -> {
                    binding.nombreUsuario.setText("")
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                RegistrarseViewModel.EstadoRegistro.ERROR -> {
                    binding.nombreUsuario.setText("")
                    binding.notificacion.text = getString(R.string.nombre_ya_registrado)
                }
            }
        })

    }

    fun setListeners() {

        registrarse.setOnClickListener() {
            saveAndNavigate()
        }
    }

    fun saveAndNavigate() {

        val nombre = nombreUsuario.text.toString()

        val usuario = Usuario(nombre)

        registrarseViewModel.save(usuario)

    }


}