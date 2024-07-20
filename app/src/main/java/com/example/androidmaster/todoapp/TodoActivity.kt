package com.example.androidmaster.todoapp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
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
        categoriesAdapter = CategoriesAdapter(categories){position -> updateCategories(position)}
        //Asignamos un layoutManager al recyclerView que hara que la vista sea horizontal o vertical
        rvCategories.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        //Asignamos el adaptador al recyclerView
        rvCategories.adapter = categoriesAdapter
        //Inicializamos el adaptador de tasks (tareas)
        taskAdapter = TaskAdapter(tasks) {position  -> onItemSelected(position)} //Tambien podemos indicarlo como {onItemSelected(it)}
        /**
         * Las funciones lambda no es necesario enviarlas dentro del parentesis
         */
        rvTasks.layoutManager = LinearLayoutManager(this) //Como es vertical basta con poner this
        rvTasks.adapter = taskAdapter

    }

    //Crearemos una funcion lambda para cuando pulsemos sobre algun elemento del recycler que tiene las tareas
    //Necesitaremos pasarle este metodo a cada uno de los items del recycler view, para ello usamos las funciones lambda
    //
    private fun onItemSelected(position : Int){

        //Esto ira al listado de tareas, buscara la tarea en la posicion enviada como
        //parametro y cambiara su valor de isSelected a lo contrario, es decir, si esta en true, lo pasara
        //a false y viceversa
        tasks[position].isSelected = !tasks[position].isSelected
        //Actualizariamos el adapter
        updateTask()

    }

    //Creamos la funcion lambda para cuando se seleccionen los items del recyclerview de categorias
    private fun updateCategories(position: Int){

        categories[position].isSelected = ! categories[position].isSelected //Lo que hara sera que cuando pulsemos sobre una categoria, su valor isSelected pasara a
        //lo contrario
        //Actualizamos el adaptador
        categoriesAdapter.notifyItemChanged(position) //Como solo cambiamos un item, basta con usar notifyItemChanged(position)

        //Para filtrar llamaremos a updateTask()
        updateTask()

    }

    //Metodo que muestra el dialogo
    private fun showDialog(){

        //Creamos el dialogo
        val dialog = Dialog(this)
        //Deberemos crear una vista del dialogo en el layout, cargarsela al dialog y decirle que la muestre
        dialog.setContentView(R.layout.dialog_task)

        //Accederemos a los componentes de la vista
        val btnAddTaskSelected : Button = dialog.findViewById(R.id.btnAddTaskSelected)
        val etTask : EditText = dialog.findViewById(R.id.etTask)
        val rgCategories : RadioGroup = dialog.findViewById(R.id.rgCategories)

        //Programamos el boton para que cuando se pulse sepamos el ultimo radioButton que hay seleccionado
        btnAddTaskSelected.setOnClickListener {
            if(etTask.text.toString().isNotEmpty()){

                //Creamos una variable para almacenar la id del elemento seleccionado del radioGroup
                val selectedId = rgCategories.checkedRadioButtonId
                //Creamos ahora el radioButton con la id que hay seleccionada
                val selectedRadioButton : RadioButton = rgCategories.findViewById(selectedId)
                //Almacenamos ahora la categoria en una variable, dependiendo del texto que almacene el radioButton se
                //creara un objeto TaskCategory u otro
                val currentCategory : TaskCategory = when(selectedRadioButton.text){
                    //Utilizamos getString(R.string.[]) para asegurar que el texto sera el mismo
                    getString(R.string.business) -> TaskCategory.Business
                    getString(R.string.personal)  -> TaskCategory.Personal
                    else -> {TaskCategory.Other}
                }

                //Creamos la nueva tarea, pasandole como parametro el texto que haya en el editText y la categoria nueva creada
                val newTask = Task(etTask.text.toString(), currentCategory)
                //Añadimos la nueva tarea
                tasks.add(newTask)
                //Indicamos al adapter que actualice los cambios
                updateTask()
                //Cerramos el dialogo
                dialog.hide() //Tambien podemos usar .dismiss()

            }else{
                Toast.makeText(this, "Debes añadir una descripcion a la nueva tarea", Toast.LENGTH_SHORT).show()
            }

        }

        dialog.show()

    }

    //Creamos un metodo para actualizar el adaptador
    private fun updateTask(){

        //Creamos un listado de categorias seleccionadas y lo igualamos a un filtro realizado en el listado de categorias donde mostraremos las seleccionadas
        val selectedCategories : List<TaskCategory> = categories.filter { it.isSelected }
        //Una vez tengamos las categorias, creamos una nueva lista de tareas, que sera el resultado de filtrar
        //dentro de la lista de tareas, las que esten en la lista selectedCategories y coincidan con la categoria de la lista de tareas
        val newTasks : List<Task> = tasks.filter { selectedCategories.contains(it.category) }
        //Indicamos que ahora la lista que tiene el adaptador, sera la nueva lista creada
        taskAdapter.tasks = newTasks
        //Notificamos los cambios
        taskAdapter.notifyDataSetChanged()
    }

}