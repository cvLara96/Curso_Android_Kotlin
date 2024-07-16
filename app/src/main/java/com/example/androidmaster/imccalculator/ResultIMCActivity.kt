package com.example.androidmaster.imccalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.androidmaster.R
import com.example.androidmaster.imccalculator.ImcCalculatorActivity.Companion.IMC_KEY

class ResultIMCActivity : AppCompatActivity() {

    //Declaramos los componentes
    private lateinit var tvResult: TextView
    private lateinit var tvIMC: TextView
    private lateinit var tvInfo: TextView
    private lateinit var btnReCalculate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_imcactivity)

        //Indicamos, del intent, si tiene extras dame el double que este bajo la clave "RESULT_IMC" si
        //no la tiene nos devuelves 1.0
        val result = intent.extras?.getDouble(IMC_KEY) ?: -1.0

        //Llamamos al metodo que inicializara los componentes
        initComponents()

        //Creamos un metodo que iniciara la interfaz de usuario, con los datos que tenga
        initUI(result)

        //Llamamos al metodo que inicializara los listener
        initListener()


    }

    //Metodo que inicia la interfaz
    private fun initUI(result: Double) {

        tvIMC.text = result.toString()

        //Filtraremos dependiendo del resultado que hayamos obtenido
        when (result) {
            in 0.00..18.49 -> {

                tvResult.text = getString(R.string.infrapeso)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.infrapeso))
                tvInfo.text = getString(R.string.infoInfrapeso)

            }

            in 18.50..24.90 -> {

                tvResult.text = getString(R.string.normal)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.normal))
                tvInfo.text = getString(R.string.infoNormal)

            }

            in 25.00..29.90 -> {

                tvResult.text = getString(R.string.sobrepeso)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.sobrepeso))
                tvInfo.text = getString(R.string.infoSobrepeso)

            }

            in 30.00..100.00 -> {

                tvResult.text = getString(R.string.obesidad)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.obesidad))
                tvInfo.text = getString(R.string.infoObesidad)

            }
            else -> {
                tvIMC.text = getString(R.string.error)
                tvResult.text = getString(R.string.error)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.obesidad))
                tvInfo.text = getString(R.string.error)
            }

        }

    }

    //Este metodo se encargara de inicializar los componentes
    private fun initListener() {

        btnReCalculate.setOnClickListener {
            onBackPressed()
        }

    }

    //Este metodo se encargara de inicializar los componentes
    private fun initComponents() {
        tvResult = findViewById(R.id.tvResult)
        tvIMC = findViewById(R.id.tvIMC)
        tvInfo = findViewById(R.id.tvInfo)
        btnReCalculate = findViewById(R.id.btnReCalculate)
    }
}