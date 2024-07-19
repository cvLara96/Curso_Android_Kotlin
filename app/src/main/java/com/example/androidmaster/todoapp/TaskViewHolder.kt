package com.example.androidmaster.todoapp

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

        tvTask.text = task.name

        when(task.category){
            TaskCategory.Business -> {
                cbTask.setTextColor(ContextCompat.getColor(cbTask.context, R.color.todo_business_category))
            }
            TaskCategory.Other -> {
                cbTask.setTextColor(ContextCompat.getColor(cbTask.context, R.color.todo_other_category))
            }
            TaskCategory.Personal -> {
                cbTask.setTextColor(ContextCompat.getColor(cbTask.context, R.color.todo_personal_category))
            }
        }

    }

}