package com.example.androidmaster.todoapp

//Sealed indica que es una clase sellada y con esto indicamos que vamos a crear objetos
//de esta clase
sealed class TaskCategory (var isSelected:Boolean = true){ //Aprovechamos la sealed class para ponerle un atributo comun a todos sus objetos

    object Personal : TaskCategory()
    object Business : TaskCategory()
    object Other : TaskCategory()

}

//Lo que haremos sera enviar al recycler view de categorias un listado de objetos TaskCategory