package ar.edu.unlam.cajeroapp.ui

import DatabaseViewModelFactory
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ar.edu.unlam.cajeroapp.R
import ar.edu.unlam.cajeroapp.model.Usuario
import kotlinx.android.synthetic.main.activity_registrarse.*

class registrarseActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrarse)

        val usuarioViewModel : UsuarioViewModel by viewModels{DatabaseViewModelFactory(applicationContext)}


        registrarse.setOnClickListener(){


            val intent = Intent (this,MainActivity::class.java)
            startActivity(intent)

        }

    }
}