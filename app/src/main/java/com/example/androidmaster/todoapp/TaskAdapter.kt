package com.example.androidmaster.todoapp

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter (private val tasks: List<Task>) :
                        RecyclerView.Adapter<TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}