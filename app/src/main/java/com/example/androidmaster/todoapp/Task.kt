package com.example.androidmaster.todoapp

//Indicamos los atributos de la clase
data class Task(val name : String, val category: TaskCategory, var isSelected : Boolean = false)
