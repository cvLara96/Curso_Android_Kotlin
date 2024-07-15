package com.example.androidmaster.syntaxys

fun main(){

    //Un array es una estructura de datos que nos permite almacenar varias variables

    //El indice de los arrays empieza en 0
    val weekDays = arrayOf("Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo")

    println(weekDays.size) //Tamaño del array

    //Para garantizar que no se accede a valores fuera de los indices podemos usar
    if(weekDays.size>=8){
        println("No hay mas valores en el array")
    }else{
        println(weekDays.size)
    }

    //Si queremos reemplazar valores podemos hacer esto
    weekDays[0] = "Nuevo valor" //Estamos accediendo al valor que hay en la posicion 0 del array (es decir lunes) y cambiando su valor

    //Para recorrer los arrays podemos utilizar bucles

    //Un bucle nos permite recorrer un array pasando por todas sus posiciones

    //El siguiente le dira "dame la posicion por cada uno de los indices", es decir ejecutara el for
    //tantas veces como indices tenga el array
    for(position in weekDays.indices){

        println("He pasado por la posicion $position que corresponde al valor ${weekDays[position]}")

    }

    //Otro for seria el siguiente:
    for((position, value) in weekDays.withIndex()){

        println("La posicion $position, contiene $value")

    }

    //El nombre de position o value se puede cambiar:
    for(weekday in weekDays){

        println("Ahora es $weekday")

    }

}