package com.example.androidmaster.imccalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidmaster.R
import com.example.androidmaster.imccalculator.ImcCalculatorActivity.Companion.IMC_KEY

class ResultIMCActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_imcactivity)

        //Indicamos, del intent, si tiene extras dame el double que este bajo la clave "RESULT_IMC" si
        //no la tiene nos devuelves 1.0
        val result = intent.extras?.getDouble(IMC_KEY) ?: -1.0

    }
}