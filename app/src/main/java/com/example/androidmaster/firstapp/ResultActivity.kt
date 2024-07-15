package com.example.androidmaster.firstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.androidmaster.R

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val tvResult = findViewById<TextView>(R.id.tvResult)

        //Crearemos el intent para recuperar la informacion de la otra actividad

        //Con esta declaracion le diremos que nos de el string almacenado bajo la clave "EXTRA_NAME"
        //y si no tiene nada bajo esa clave nos lo de vacio con .orEmpty(), tambien utilizamos el operador ?
        //para indicar que puede o no ser nulo
        val name : String = intent.extras?.getString("EXTRA_NAME").orEmpty()

        tvResult.text = "Hola $name"

    }
}