package ar.edu.unlam.cajeroapp.ui


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import ar.edu.unlam.cajeroapp.R
import ar.edu.unlam.cajeroapp.databinding.ActivityTransferenciaBinding
import kotlinx.android.synthetic.main.activity_home.transferir
import kotlinx.android.synthetic.main.activity_transferencia.*
import org.koin.android.viewmodel.ext.android.viewModel


class TransferenciaActivity : AppCompatActivity() {

    private val miViewModel: TrasnferenciaViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityTransferenciaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListeners()
        val idUsuario = intent.getLongExtra(HomeActivity.USER_ID_PARAM, 200)
        miViewModel.buscarCuentaPorIdDeUsuario(idUsuario)

        miViewModel.estadoTrasferencia.observe(this, Observer {
            when (it) {
                TrasnferenciaViewModel.EstadoTransferencia.OK -> {

                    notificacion.text = getString(R.string.transferencia_ok)

                    val intent = Intent(this, DatosTrasnferenciaActivity::class.java)

                    intent.putExtra("monto",binding.monto.text.toString())
                    intent.putExtra("nombreUsuarioReceptor",binding.nombreUsuario.text.toString())
                    intent.putExtra("nombreUsuarioSalida", miViewModel.usuario.value?.nombre.toString())

                    startActivity(intent)

                }
                TrasnferenciaViewModel.EstadoTransferencia.ERROR -> {
                    binding.monto.setText("")
                    binding.nombreUsuario.setText("")
                    notificacion.text = getString(R.string.transferencia_error)
                }
                TrasnferenciaViewModel.EstadoTransferencia.DINEROINSUFICIENTE -> {
                    binding.monto.setText("")
                    binding.nombreUsuario.setText("")
                    notificacion.text = getString(R.string.dinero_insuficiente)
                }
                TrasnferenciaViewModel.EstadoTransferencia.USUARIONOENCONTRADO -> {
                    binding.monto.setText("")
                    binding.nombreUsuario.setText("")
                    notificacion.text = getString(R.string.usuario_no)
                }
            }
        })


    }

    private fun setListeners() {

        transferir.setOnClickListener() {

            val montoATrasferir = monto.text.toString()
            val nombreUsuarioATrasferir = nombreUsuario.text.toString()

            miViewModel.transferir(montoATrasferir, nombreUsuarioATrasferir)


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


