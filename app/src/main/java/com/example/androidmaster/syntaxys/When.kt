package com.example.androidmaster.syntaxys

fun main(){

    getMonth(10)

    getTrimester(4)

    getSemester(13)

    result("prueba de result")


}

//La variable de tipo Any indica que el valor puede ser lo que se quiera
//pero hay que tener cuidado con el uso de esta
fun result(value:Any){

    when(value){

        is Int -> println(value + value)
        is String -> println(value)
        is Boolean -> println(value)

    }

}

fun getMonth(month : Int){

    when(month){
        1-> println("Enero")
        2-> println("Febrero")
        3-> println("Marzo")
        4-> println("Abril")
        5-> println("Mayo")
        6-> println("Junio")
        7-> println("Julio")
        8-> println("Agosto")
        9-> println("Septiembre")
        //Si queremos que haya varias lineas, meteremos el codigo entre llaves{}
        10-> {
            println("Octubre")
            println("Mes numero 10")
        }
        11-> println("Noviembre")
        12-> println("Diciembre")
        else -> println("No es un mes valido")
    }

}

fun getTrimester(month: Int){

    when(month){
        1, 2, 3-> println("Primer trimestre")
        4, 5, 6-> println("Segundo trimestre")
        7, 8, 9-> println("Tercer trimestre")
        10, 11, 12-> println("Cuarto trimestre")
        else -> println("Trimestre no valido")
    }

}

//Este lo haremos distinto, haremos que devuelva un String
fun getSemester(month: Int) : String{

    //Para ello podemos crear una variable y la igualamos al when,
    //es decir, le estaremos diciendo que almacene en la variable el resultado
    //del when

    val result = when(month){

        //Con in podemos establecer rangos
        in 1..6 -> "Primer semestre"
        in 7..12-> "Segundo semestre"
        else -> "Semestre no valido"
        //Otra opcion en lugar de else seria:
        //!in 1 .. 12 -> println("semestre no valido")
    }

    return result

}

//Mas opciones del metodo anterior
fun getSemester2(month: Int) : String{

    return when(month){

        //Con in podemos establecer rangos
        in 1..6 -> "Primer semestre"
        in 7..12-> "Segundo semestre"
        else -> "Semestre no valido"
        //Otra opcion en lugar de else seria:
        //!in 1 .. 12 -> println("semestre no valido")
    }

}

//Mas opciones del metodo anterior
fun getSemester3(month: Int) = when(month){

        //Con in podemos establecer rangos
        in 1..6 -> "Primer semestre"
        in 7..12-> "Segundo semestre"
        else -> "Semestre no valido"
        //Otra opcion en lugar de else seria:
        //!in 1 .. 12 -> println("semestre no valido")
}





