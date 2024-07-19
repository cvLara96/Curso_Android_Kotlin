package com.example.androidmaster.todoapp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidmaster.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TodoActivity : AppCompatActivity() {

    //Creamos referencia a los componentes

    //----------------------

    //RecyclerView
    private lateinit var rvCategories : RecyclerView
    //Creamos el adaptador
    private lateinit var categoriesAdapter: CategoriesAdapter
    /**
     * Para que el recyclerview funcione deberemos crear un adaptador y un viewHolder
     *  - El adaptador sera la clase que conectara toda la informacion con el recyclerView
     *  - El ViewHolder sera el encargado de pintarlo
     */
    private lateinit var rvTasks : RecyclerView
    private lateinit var taskAdapter : TaskAdapter

    //----------------------

    //FloatingButton
    private lateinit var btnAddTask : FloatingActionButton

    //----------------------

    //Listados

    //Creamos el listado de categorias que enviaremos al adaptador
    private val categories = listOf<TaskCategory>(
        TaskCategory.Business,
        TaskCategory.Other,
        TaskCategory.Personal
    )

    //Creamos el listado de tareas, actualmente estara vacio
    private val tasks : MutableList<Task> = mutableListOf(

        //Creamos tres tareas de prueba
        Task("Prueba_Business", TaskCategory.Business),
        Task("Prueba_Personal", TaskCategory.Personal),
        Task("Prueba_Other", TaskCategory.Other)
    )

    //----------------------

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)

        //Metodo de inicializar componentes
        initComponents()
        //Metodo que iniciara la IU
        initIU()
        //Metodo que inidica los listener
        initListener()

    }

    //Metodo que referencia los componentes
    private fun initComponents() {
        rvCategories = findViewById(R.id.rvCategories)
        rvTasks = findViewById(R.id.rvTasks)
        btnAddTask = findViewById(R.id.btnAddTask)
    }

    //Metodo que inicia los listener
    private fun initListener() {

        btnAddTask.setOnClickListener {
            showDialog()
        }
    }

    //Metodo que inicia la interfaz de usuario
    private fun initIU() {

        //Inicializamos el adaptador de categorias pasandole el listado de categorias
        categoriesAdapter = CategoriesAdapter(categories)
        //Asignamos un layoutManager al recyclerView que hara que la vista sea horizontal o vertical
        rvCategories.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        //Asignamos el adaptador al recyclerView
        rvCategories.adapter = categoriesAdapter

        //Inicializamos el adaptador de tasks (tareas)
        taskAdapter = TaskAdapter(tasks)
        rvTasks.layoutManager = LinearLayoutManager(this) //Como es vertical basta con poner this
        rvTasks.adapter = taskAdapter

    }

    //MEtodo que muestra el dialogo
    private fun showDialog(){

        //Creamos el dialogo
        val dialog = Dialog(this)
        //Deberemos crear una vista del dialogo en el layout

    }

}