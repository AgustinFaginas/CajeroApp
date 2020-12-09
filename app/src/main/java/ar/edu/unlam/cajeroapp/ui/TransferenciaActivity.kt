package ar.edu.unlam.cajeroapp.ui


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import ar.edu.unlam.cajeroapp.R
import ar.edu.unlam.cajeroapp.databinding.ActivityTransferenciaBinding
import org.koin.android.viewmodel.ext.android.viewModel


class TransferenciaActivity : AppCompatActivity() {


    private lateinit var binding: ActivityTransferenciaBinding
    private val miViewModel: TrasnferenciaViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding = ActivityTransferenciaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListeners()
        val idUsuario = intent.getLongExtra(HomeActivity.USER_ID_PARAM, 200)
        miViewModel.buscarCuentaPorIdDeUsuario(idUsuario)

        miViewModel.estadoTrasferencia.observe(this, Observer {
            when (it) {
                TrasnferenciaViewModel.EstadoTransferencia.OK -> {

                    binding.notificacion.text = getString(R.string.transferencia_ok)

                    val intent = Intent(this, DatosTrasnferenciaActivity::class.java)

                    intent.putExtra("monto",binding.monto.text.toString())
                    intent.putExtra("nombreUsuarioReceptor",binding.nombreUsuario.text.toString())
                    intent.putExtra("nombreUsuarioSalida", miViewModel.usuario.value?.nombre.toString())

                    startActivity(intent)

                }
                TrasnferenciaViewModel.EstadoTransferencia.ERROR -> {
                    binding.monto.setText("")
                    binding.nombreUsuario.setText("")
                    binding.notificacion.text = getString(R.string.transferencia_error)
                }
                TrasnferenciaViewModel.EstadoTransferencia.DINEROINSUFICIENTE -> {
                    binding.monto.setText("")
                    binding.nombreUsuario.setText("")
                    binding.notificacion.text = getString(R.string.dinero_insuficiente)
                }
                TrasnferenciaViewModel.EstadoTransferencia.USUARIONOENCONTRADO -> {
                    binding.monto.setText("")
                    binding.nombreUsuario.setText("")
                    binding.notificacion.text = getString(R.string.usuario_no)
                }
            }
        })


    }

    private fun setListeners() {


        binding.transferir.setOnClickListener() {

            val montoATrasferir = binding.monto.text.toString()
            val nombreUsuarioATrasferir = binding.nombreUsuario.text.toString()

            miViewModel.transferir(montoATrasferir, nombreUsuarioATrasferir)


        }


        binding.salir.setOnClickListener() {
            finish()
        }


    }

    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }

}


