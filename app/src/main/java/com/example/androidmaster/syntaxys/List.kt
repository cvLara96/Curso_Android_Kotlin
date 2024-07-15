package com.example.androidmaster.syntaxys

fun main(){

    //inmutableList()
    mutableList()
}

fun inmutableList(){

    //Lista de solo lectura
    val readOnly : List<String> = listOf("Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo")

    println(readOnly.size) //Nos devuelve el tamaño
    println(readOnly) //Devuelve los valores de la lista
    println(readOnly[0]) //Devuelve el valor en la posicion 0
    println(readOnly.last()) //Devuelve el ultimo valor de la lista
    println(readOnly.first()) //Devuelve el primer valor de la lista

    //FILTRAR: Podemos utilizar la lista para filtrar
    //filter recorrera una a una todas las posiciones, it sera el valor en cada posicion
    val example = readOnly.filter{it.contains("a")}
    println(example) //Devolvera los valores filtrados que contengan "a"

    //RECORRER LISTAS:

    readOnly.forEach{ println(it)}
    //Tambien se puede indicar de esta forma, seria lo mismo, pero es mas legible y recomendable
    readOnly.forEach{weekDay -> println(weekDay)}

}

fun mutableList(){

    //Lista en la cual podemos modificar, añadir, o eliminar valores
    val weekDays : MutableList<String> = mutableListOf("Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo")
    println(weekDays)//Devuelve los valores de la lista

    //Si queremos añadir un valor nuevo al listado
    weekDays.add("Valor añadido") //add por defecto añade el valor en la ultima posicion
    println(weekDays)

    //Si le pasamos el indice donde queremos añadirlo, lo colocara ahi
    weekDays.add(2, "Valor añadido V2")
    println(weekDays)

    //Tambien podemos comprobar si esta vacia
    if(weekDays.isEmpty()){
        //No hay valores
    }else{
        weekDays.forEach { weekDay -> println(weekDay) }
    }

    //Podemos comprobar que no esta vacia
    if(weekDays.isNotEmpty()) {
        weekDays.forEach {weekDay -> println(weekDay)}

    }

    //Tambien podremos filtrar

    //La diferencia respecto a las no mutables, es que estas se pueden modificar

}