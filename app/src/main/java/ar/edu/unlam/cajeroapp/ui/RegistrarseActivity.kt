package ar.edu.unlam.cajeroapp.ui


import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import ar.edu.unlam.cajeroapp.R
import ar.edu.unlam.cajeroapp.databinding.ActivityRegistrarseBinding
import ar.edu.unlam.cajeroapp.model.Usuario
import org.koin.android.ext.android.bind
import org.koin.android.viewmodel.ext.android.viewModel


private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)

class RegistrarseActivity : AppCompatActivity() {

    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>
    private lateinit var binding: ActivityRegistrarseBinding



    private val registrarseViewModel: RegistrarseViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrarseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListeners()

        crearPermisosLauncher()

        val correctphoto = intent.extras?.get("imagen_ok")

        if(correctphoto !=null){
            binding.notificacionDni.text = "Imagen dni correcta"
            binding.fotoDni.isEnabled = false

        }



        binding.fotoDni.setOnClickListener { launchCamaraClicked() }

        registrarseViewModel.estadoRegistracion.observe(this, Observer {
            when (it) {
                RegistrarseViewModel.EstadoRegistro.OK -> {
                    binding.nombreUsuario.setText("")
                    val intent = Intent(this, InicioSesionActivity::class.java)
                    startActivity(intent)
                }
                RegistrarseViewModel.EstadoRegistro.ERROR -> {
                    binding.nombreUsuario.setText("")
                    binding.notificacion.text = getString(R.string.error)
                }
                RegistrarseViewModel.EstadoRegistro.EXISTEUSUARIO -> {
                    binding.nombreUsuario.setText("")
                    binding.notificacion.text = getString(R.string.nombre_ya_registrado)
                }


            }
        })



    }

    private fun setListeners() {


        binding.registrarse.setOnClickListener() {
            saveAndNavigate()
        }
    }

    private fun saveAndNavigate() {


        val usuario = Usuario(binding.nombreUsuario.text.toString())

        val correctphoto = intent.extras?.get("imagen_ok")
        if (correctphoto !=null){
        registrarseViewModel.guardarUsuario(usuario)
        }else{
            binding.notificacion.text = "Debe tomar foto a su documento"
        }

    }




    private fun crearPermisosLauncher() {
        permissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
                if (permissions[Manifest.permission.CAMERA] == true) {
                    launchCamera()
                } else {
                    Toast.makeText(
                        this,
                        "Debes conceder permisos para lanzar la camara",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun launchCamaraClicked() {
        if (arePermissionsGranted()) {
            launchCamera()
        } else {
            askPermissions()
        }

    }

    private fun arePermissionsGranted(): Boolean {
        return REQUIRED_PERMISSIONS.all {
            ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED
        }
    }

    private fun askPermissions() {
        permissionLauncher.launch(REQUIRED_PERMISSIONS)
    }


    private fun launchCamera() {
        startActivity(Intent(this, CamaraActivity::class.java))
    }

}







