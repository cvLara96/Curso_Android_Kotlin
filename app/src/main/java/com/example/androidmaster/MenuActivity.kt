package com.example.androidmaster

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.androidmaster.R
import com.example.androidmaster.firstapp.FirstAppActivity
import com.example.androidmaster.imccalculator.ImcCalculatorActivity
import com.example.androidmaster.settings.SettingsActivity
import com.example.androidmaster.superheroapp.SuperHeroListActivity
import com.example.androidmaster.todoapp.TodoActivity
import okhttp3.internal.http2.Settings

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val btnSaludApp = findViewById<Button>(R.id.btnSaludaApp)
        val btnIMCApp = findViewById<Button>(R.id.btnIMCApp)
        val btnToDo = findViewById<Button>(R.id.btnToDo)
        val btnSuperHero = findViewById<Button>(R.id.btnSuperHeroApp)
        val btnSettings = findViewById<Button>(R.id.btnSettings)

        btnSaludApp.setOnClickListener { navigateToSaludApp() }
        btnIMCApp.setOnClickListener { navigateToIMCApp() }
        btnToDo.setOnClickListener { navigatetoToDoApp() }
        btnSuperHero.setOnClickListener { navigateToSuperHeroApp() }
        btnSettings.setOnClickListener { navigateToSettings() }

    }

    //Metodo para ir a la activity de IMC
    private fun navigateToIMCApp() {
        val intent = Intent(this, ImcCalculatorActivity::class.java)
        startActivity(intent)
    }

    //Metodo para ir a la activity de saludar
    private fun navigateToSaludApp() {
        val intent = Intent(this, FirstAppActivity::class.java)
        startActivity(intent)
    }

    //Metodo para ir a la activity To-Do App
    private fun navigatetoToDoApp() {
        val intent = Intent(this, TodoActivity::class.java)
        startActivity(intent)
    }

    //Metodo para ir a la actividad SuperHero App
    private fun navigateToSuperHeroApp() {
        val intent = Intent(this, SuperHeroListActivity::class.java)
        startActivity(intent)
    }

    //Metodo para ir a la actividad de settings
    private fun navigateToSettings() {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }

}