package ar.edu.unlam.cajeroapp.ui

import android.content.Intent
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import ar.edu.unlam.cajeroapp.R
import ar.edu.unlam.cajeroapp.model.Cuenta
import ar.edu.unlam.cajeroapp.model.Usuario
import kotlinx.android.synthetic.main.activity_registrarse.*
import org.koin.android.viewmodel.ext.android.viewModel

class RegistrarseActivity: AppCompatActivity() {
    val registrarseViewModel : RegistrarseViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrarse)

        setListeners()

    }
    fun setListeners(){

        registrarse.setOnClickListener(){
            saveAndNavigate()
        }
    }

    fun saveAndNavigate(){

        val nombre = nombreUsuario.text.toString()

        val usuario = Usuario(nombre)

        registrarseViewModel.save(usuario)


        val intent = Intent(this ,MainActivity::class.java)

        startActivity(intent)
    }

    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }

}