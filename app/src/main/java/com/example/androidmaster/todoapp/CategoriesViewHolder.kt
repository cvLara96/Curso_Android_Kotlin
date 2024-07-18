package com.example.androidmaster.todoapp

import android.provider.Settings.Global.getString
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.androidmaster.R

//3.- Recibira como parametro una vista o View y extendera de RecyclerView.ViewHolder() y recibira como parametro una vista o view
class CategoriesViewHolder (view : View) : RecyclerView.ViewHolder(view){

    //5.4.- Finalmente pintaremos en la vista la informacion del objeto, para ello crearemos primero
    //los elementos que componen la vista, en este caso solo es un textView y una View
    //Para referenciarlo hacemos uso del view que contiene toda la informacion de la vista
    private val tvCategoryName : TextView = view.findViewById(R.id.tvCategoryName)
    private val divider : View = view.findViewById(R.id.divider)


    //5.3.1. La funcion se llamara render y recibira un objeto de la clase TaskCategory
    fun render (taskCategory : TaskCategory){

        //5.4.1.- Asignaremos los valores del objeto que recibe como parametro a los elementos de la vista
        //Para ello nos ayudaremos con un when() que filtrara dependiendo del taskCategory que se este recibiendo
        when(taskCategory){
            TaskCategory.Business -> {
                tvCategoryName.text = "Negocios"
                divider.setBackgroundColor(ContextCompat.getColor(divider.context,R.color.todo_business_category))
            }
            TaskCategory.Other -> {
                tvCategoryName.text = "Otros"
                divider.setBackgroundColor(ContextCompat.getColor(divider.context,R.color.todo_other_category))
            }
            TaskCategory.Personal -> {
                tvCategoryName.text = "Personal"
                divider.setBackgroundColor(ContextCompat.getColor(divider.context,R.color.todo_personal_category))
            }
        }


    }

    //5.5. -> Volveremos a la actividad desde la que llamamos al adapter para asignar el adaptador al recycler

}