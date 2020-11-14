package ar.edu.unlam.cajeroapp.ui


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import ar.edu.unlam.cajeroapp.R
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home.transferir
import kotlinx.android.synthetic.main.activity_transferencia.*
import kotlinx.android.synthetic.main.activity_transferencia.notificacion
import kotlinx.android.synthetic.main.activity_transferencia.salir

import org.koin.android.viewmodel.ext.android.viewModel


class TransferenciaActivity : AppCompatActivity() {

    private val miViewModel: TrasnferenciaViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transferencia)

        setListeners()
        val idUsuario = intent.getLongExtra(HomeActivity.USER_ID_PARAM, 200)
        miViewModel.buscarCuentaPorIdDeUsuario(idUsuario)

        miViewModel.estadoTrasferencia.observe(this, Observer {
            when (it) {
                TrasnferenciaViewModel.EstadoTransferencia.OK -> {
                    monto.setText("")
                    nombreUsuario.setText("")
                    notificacion.text=getString(R.string.transferencia_ok)
                }
                TrasnferenciaViewModel.EstadoTransferencia.ERROR -> {
                    monto.setText("")
                    nombreUsuario.setText("")
                    notificacion.text=getString(R.string.transferencia_error)
                }
            }
        })


    }

    private fun setListeners() {

        transferir.setOnClickListener() {

            miViewModel.transferir(monto.text.toString(), nombreUsuario.text.toString())


        }

        salir.setOnClickListener() {
            finish()
        }


    }


    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }
}