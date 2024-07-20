package com.example.androidmaster.todoapp

import android.content.res.ColorStateList
import android.graphics.Paint
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.androidmaster.R

class TaskViewHolder (view : View) : RecyclerView.ViewHolder(view){

    private val tvTask : TextView = view.findViewById(R.id.tvTask)
    private val cbTask : CheckBox = view.findViewById(R.id.cbTask)
    //En este caso recibira como parametro un objeto de una DataClass llamada Task
    fun render (task : Task){

        //Controlaremos si la tarea ha sido seleccionada, y si lo esta que la tache
        if(task.isSelected){
            tvTask.paintFlags = tvTask.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG //Esto lo que hace es tachar el texto
        }else{
            tvTask.paintFlags = tvTask.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv() //Esto lo que hace es destachar el texto
        }

        tvTask.text = task.name
        cbTask.isChecked = task.isSelected //Es decir, si hemos seleccionado la tarea, se marca tambien el checkbox

        //Para darle color al checkBox
        /**
         * PARA ACCEDER AL CONTEXT EL TRUCO ES HACERLO A TRAVES DE LAS VISTAS, EN ESTE CASO PODEMOS USAR cbTask.context
         */
        //Creamos una variable que almacenara el color dependiendo de la categoria, y ese color se le pasaremos al ButtonTintList
        val color = when(task.category){
            TaskCategory.Business -> R.color.todo_business_category
            TaskCategory.Other -> R.color.todo_other_category
            TaskCategory.Personal -> R.color.todo_personal_category
        }

        //De esta forma no repetimos este codigo
        cbTask.buttonTintList = ColorStateList.valueOf(
            ContextCompat.getColor(cbTask.context, color)
        )


    }

}