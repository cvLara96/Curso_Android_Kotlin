package com.example.androidmaster.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.androidmaster.R

class TodoActivity : AppCompatActivity() {

    //Creamos referencia a los componentes
    private lateinit var rvCategories : RecyclerView

    /**
     * Para que el recyclerview funcione deberemos crear un adaptador y un viewHolder
     *  - El adaptador sera la clase que conectara toda la informacion con el recyclerView
     *  - El ViewHolder sera el encargado de pintarlo
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)

        //Metodo de inicializar componentes
        initComponents()

        //Metodo que iniciara la IU
        initIU()

    }

    //Metodo que referencia los componentes
    private fun initComponents() {
        rvCategories = findViewById(R.id.rvCategories)
    }

    private fun initIU() {

    }
}