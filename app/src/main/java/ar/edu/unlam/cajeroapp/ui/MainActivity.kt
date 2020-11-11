package ar.edu.unlam.cajeroapp.ui

import android.content.Intent
import android.content.IntentSender
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import ar.edu.unlam.cajeroapp.R
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.notificacion
import org.koin.android.viewmodel.ext.android.viewModel



class MainActivity : AppCompatActivity() {

    private val usuarioViewModel : UsuarioViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setListeners()

        usuarioViewModel.usuario.observe(this, Observer { it })




    }


        private fun setListeners(){

            ingresar.setOnClickListener() {

               usuarioViewModel.getByName(nombreUsuario.toString())
                if (usuarioViewModel.usuario.value !=null){
                    val intent = Intent(this , HomeActivity::class.java)
                    startActivity(intent)
                }else{

                    notificacion.text = getString(R.string.usuario_no)
                }

            }

            registrarse.setOnClickListener(){


                val intent = Intent (this,RegistrarseActivity::class.java)
                startActivity(intent)

            }
        }


    }








