package ar.edu.unlam.cajeroapp.ui

import DatabaseViewModelFactory
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ar.edu.unlam.cajeroapp.R
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val usuarioViewModel : UsuarioViewModel by viewModel()


        ingresar.setOnClickListener(){



            val intent = Intent (this,HomeActivity::class.java)
            startActivity(intent)
        }

        registrarse.setOnClickListener(){


            val intent = Intent (this,registrarseActivity::class.java)
            startActivity(intent)

        }



    }
}







