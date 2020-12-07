package ar.edu.unlam.cajeroapp.ui

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import ar.edu.unlam.cajeroapp.R
import ar.edu.unlam.cajeroapp.databinding.ActivityDatosTransferenciaBinding
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException


class DatosTrasnferenciaActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDatosTransferenciaBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.usuarioEntrada.text = intent.extras!!.getString("nombreUsuarioReceptor")
        binding.usuarioSalida.text = intent.extras!!.getString("nombreUsuarioSalida")
        binding.montoTrasnferencia.text = intent.extras!!.getString("monto")



        binding.guardar.setOnClickListener {
            tomarYGuardarScreenshot()
            binding.notificacion.text = getString(R.string.screenshot_ok)
        }
    }

    private fun tomarYGuardarScreenshot() {

        val linearLayout: LinearLayout = findViewById(R.id.container)

        val screenshot = getScreenShot(linearLayout)

        val stream = ByteArrayOutputStream()

        screenshot.compress(Bitmap.CompressFormat.PNG, 100, stream)

        val byteArray: ByteArray = stream.toByteArray()

        try {

            val outputStream: FileOutputStream =
                applicationContext.openFileOutput("imagen.gif", Context.MODE_PRIVATE)
            outputStream.write(byteArray)
            outputStream.close()

        } catch (e: FileNotFoundException) {
            e.printStackTrace()

        } catch (e2: IOException) {

            e2.printStackTrace()
        }
    }


    private fun getScreenShot(view: View): Bitmap {
        val returnedBitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(returnedBitmap)
        val bgDrawable = view.background
        if (bgDrawable != null) bgDrawable.draw(canvas)
        else canvas.drawColor(Color.WHITE)
        view.draw(canvas)
        return returnedBitmap
    }


}