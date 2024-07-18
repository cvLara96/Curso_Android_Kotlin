package com.example.androidmaster.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
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

    //Creamos el adaptador
    private lateinit var categoriesAdapter: CategoriesAdapter
    //Creamos el listado de categorias que enviaremos al adaptador
    private val categories = listOf<TaskCategory>(
        TaskCategory.Business,
        TaskCategory.Other,
        TaskCategory.Personal
    )

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

        //Inicializamos el adaptador de categorias pasandole el listado de categorias
        categoriesAdapter = CategoriesAdapter(categories)
        //Asignamos un layoutManager al recyclerView que hara que la vista sea horizontal o vertical
        rvCategories.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        //Asignamos el adaptador al recyclerView
        rvCategories.adapter = categoriesAdapter

    }
}