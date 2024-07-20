package com.example.androidmaster.todoapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidmaster.R

//1.- Esta clase debera recibir un parametro que sera una lista de objetos TaskCategory (y tambien una funcion lambda que se ha creado despues)
//4.- Indicaremos que hereda de la clase RecyclerView.Adapter y recibira como parametro el nombre de la clase ViewHolder
class CategoriesAdapter(private val categories: List<TaskCategory>, private val onItemSelected : (Int) -> Unit) :
    RecyclerView.Adapter<CategoriesViewHolder>() {

    //2.- Creamos la clase CategoriesViewHolder -->

    //5.- Nos solicitara implementar 3 metodos

    //5.2.- Este metodo lo que hara sera crear una vista y montar esa vista para que luego el metodo onBindViewHolder pueda pasarle lo que debe mostrar
    //5.2.1.- Para ello deberemos crear una vista en layout ->
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {

        //5.2.3.- Creamos un inflater, para ello tenemos que conseguir el contexto y utilizamos el de parent
        //Y le indicaremos que infle una vista con .inflate, la vista que inflara sera la creada en el layout en el punto 5.2.2
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task_category, parent, false)

        //5.2.4.- devolveremos un ViewHolder (CategoriesViewHolder) que recibira como parametro la view que acabamos de crear
        return CategoriesViewHolder(view)

    }

    //5.3.- Crearemos una funcion en el ViewHolder ->
    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {

        //5.3.2.- Con la funcion render creada, la llamamos desde este metodo a traves del holder
        //Como necesita recibir un objeto taskCategory del listado, a traves de la posicion le enviaremos el que proceda

        //(En este caso se envia a la funcion render del ViewHolder la funcion lambda, para enviarla debe ir sin parentesis)
        holder.render(categories[position], onItemSelected)

    }

    //5.1. Este metodo devolvera el tamaño del listado
    override fun getItemCount(): Int {
        return categories.size
    }


}