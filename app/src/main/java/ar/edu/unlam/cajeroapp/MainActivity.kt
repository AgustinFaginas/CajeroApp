package ar.edu.unlam.cajeroapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val miViewModel: MainViewModel by viewModels()

        depositar50.setOnClickListener(){
            miViewModel.dineroActual= miViewModel.dineroActual + 50

            dineroEnCuenta.setText(" ${miViewModel.dineroActual}")

        }


    }
}