package ar.edu.unlam.cajeroapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import ar.edu.unlam.cajeroapp.R
import ar.edu.unlam.cajeroapp.databinding.ActivityHomeBinding
import org.koin.android.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {


    private val miViewModel: HomeViewModel by viewModel()
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
         binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)





        miViewModel.cuenta.observe(this, Observer { binding.dineroEnCuenta.text = it.dinero.toString() })
        miViewModel.estadoDeposito.observe(this, Observer {
            when (it) {

                HomeViewModel.EstadoDeposito.DEPOSITO_OK -> {
                    binding.dineroADepositar.setText("")
                    binding.notificacion.text = getString(R.string.dinero_ok)
                }

                HomeViewModel.EstadoDeposito.STRING_VACIO -> binding.notificacion.text =
                    getString(R.string.suma_invalida)

                HomeViewModel.EstadoDeposito.ERROR -> binding.notificacion.text =
                    getString(R.string.suma_invalida)

                HomeViewModel.EstadoDeposito.EXTRACCION_OK -> {
                    binding.dineroADepositar.setText("")
                    binding.notificacion.text = getString(R.string.extraccion_ok)
                }
                HomeViewModel.EstadoDeposito.DINERO_INSUFICIENTE -> {
                    binding.dineroADepositar.setText("")
                    binding.notificacion.text = getString(R.string.dinero_insuficiente)
                }

            }
        })
        val idUsuario = intent.getLongExtra(USER_ID_PARAM, 200)
        miViewModel.buscarCuentaPorIdDeUsuario(idUsuario)
        setListeners()
    }

    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }


    fun setListeners() {

        var idUsuario = intent.getLongExtra(USER_ID_PARAM, 0)
        binding.depositar.setOnClickListener() {

            miViewModel.depositar( binding.dineroADepositar.text.toString())

        }



        binding.extraer.setOnClickListener() {

            miViewModel.extraer( binding.dineroAExtraer.text.toString())

        }

        binding.salir.setOnClickListener() {
            binding.dineroEnCuenta.text = "0"
            finish()
        }

        binding.transferir.setOnClickListener() {
            val intent = Intent(this, TransferenciaActivity::class.java)
            intent.putExtra(HomeActivity.USER_ID_PARAM, idUsuario)
            startActivity(intent)
        }
    }

    companion object {
        const val USER_ID_PARAM = "USER_ID_PARAM"
    }
}










