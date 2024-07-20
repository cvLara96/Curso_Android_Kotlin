package com.example.androidmaster.todoapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidmaster.R

//Al adaptador para pasarle una funcion lambda lo que tenemos que indicar es que recibira como parametro una funcion
//onTaskSelected es el nombre que hemos elegido, recibira un Int como parametro que indicaremos entre parentesis y seguido de -> Unit
class TaskAdapter (var tasks: List<Task>, private val onTaskSelected : (Int) -> Unit) :
                        RecyclerView.Adapter<TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_to_do_task, parent, false)

        return TaskViewHolder(view)

    }

    override fun getItemCount(): Int {
        return tasks.size

        /**
         * Podriamos resumirlo en
         * override fun getItemCount() = task.size
         */
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.render(tasks[position])
        //Aqui podremos llamar a la funcion onTaskSelected, y cuando se active recorrera el camino a la inversa hasta llegar a
        //la declaracion del metodo, es decir, ira a la clase TodoActivity, y ahi ejecutara la funcion que se ha enviado como
        //parametro al adapter, es decir, onItemSelected()
        //Para ello, utilizamos holder.itemView, para que acceda a la celda del recyclerView y le asigne un listener
        holder.itemView.setOnClickListener {
            onTaskSelected(position)
        }

    }
}