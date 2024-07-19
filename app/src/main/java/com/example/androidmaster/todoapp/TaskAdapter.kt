package com.example.androidmaster.todoapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidmaster.R

class TaskAdapter (private val tasks: List<Task>) :
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
    }
}