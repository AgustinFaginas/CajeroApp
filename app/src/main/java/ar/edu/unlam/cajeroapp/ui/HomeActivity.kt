package ar.edu.unlam.cajeroapp.ui

import kotlinx.android.synthetic.main.activity_home.*



import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import ar.edu.unlam.cajeroapp.R
import org.koin.android.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private val miViewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        miViewModel.buscarCuentaPorIdDeUsuario(intent.getLongExtra(USER_ID_PARAM,0))
        setListeners()

        miViewModel.estado.observe(this, Observer { dineroEnCuenta.text = it.toString() })
        miViewModel.estadoDeposito.observe(this, Observer {
            when (it) {

                HomeViewModel.EstadoDeposito.DEPOSITO_OK -> {
                    dineroADepositar.setText("")
                    notificacion.text = getString(R.string.dinero_ok)
                }

                HomeViewModel.EstadoDeposito.STRING_VACIO -> notificacion.text =
                    getString(R.string.suma_invalida)

                HomeViewModel.EstadoDeposito.ERROR -> notificacion.text =
                    getString(R.string.suma_invalida)

                HomeViewModel.EstadoDeposito.EXTRACCION_OK -> {
                    dineroADepositar.setText("")
                    notificacion.text = getString(R.string.extraccion_ok)
                }
            }
        })
    }





    fun setListeners(){

        var idUsuario = intent.getLongExtra(USER_ID_PARAM,0)
        depositar.setOnClickListener() {

            miViewModel.depositar(dineroADepositar.text.toString(),idUsuario)

        }

        extraer.setOnClickListener() {

            miViewModel.extraer(dineroAExtraer.text.toString(),idUsuario)

        }
    }
    companion object {
        const val USER_ID_PARAM = "USER_ID_PARAM"
    }
    }










