package com.example.androidmaster.syntaxys

//Si instanciamos aqui la variable, sera una variable de clase y todos podran acceder a ella
val intExample : Int = 28

fun main(){

    //En Kotlin el tipo de variable se especifica con : y el tipo de variable
    //val indicara que el valor de la variable no se puede cambiar
    //var indicara que el valor de la variable si puede cambiar

    //----------LLAMADA A FUNCIONES DESDE MAIN----------
    variablesNumericas() //Funcion sin parametros
    showMyName("Carlos") //Funcion con un parametros String
    showMyAge(28) // Funcion con un parametro numerico
    add(3,2) //Funcion con dos parametros numericos

        //LLAMAR A FUNCION QUE DEVUELVE UN VALOR O QUE TIENE PARAMETROS DE SALIDA
    val mySubtract = substract(10,5)
    println(mySubtract)

}

//----------FUNCIONES----------

fun showMyName(name : String){

    println("Me llamo $name")

}

fun showMyAge(age : Int){
//Si queremos que tenga un valor por defecto podemos
    //indicar esto en la funcion
    //fun showMyAge(age : Int = 30) {
    // }

    println ("Tengo $age años")
}

fun add(firstNumber:Int, secondNumber:Int){
    println("La suma de $firstNumber y $secondNumber es = ${firstNumber + secondNumber}")
}

//Esta funcion devolvera un valor, por eso, despues de los parametros se indica el tipo de valor devuelto
fun substract(firstNumber: Int, secondNumber: Int) : Int{
    //Para devolver el valor hay que poner return
    return firstNumber - secondNumber

}

//Podemos juntar una funcion que devuelve parametros en una sola linea de la siguiente forma
fun subtractCool(firstNumber: Int, secondNumber: Int) : Int = firstNumber - secondNumber

fun variablesNumericas(){

    //----------VARIABLES NUMERICAS----------

    //Int o entero

    var intExampleVar : Int = 27
    println(intExampleVar)
    intExampleVar = 28 //Como es un var, podremos modificar su valor
    println(intExampleVar)

    //Double, para decimales
    val doubleExample : Double = 10.50

    //Long
    val longExample : Long = 3000000000000000000

    //Float, se usa para decimales, soporta hasta 6 decimales. Importante indicar F despues del numero
    val floatExample : Float = 10.5451f

    //----------OPERACIONES CON VARIABLES----------

    //Podremos concatenar utilizando $nombre de la variable o ${operacion con variables}

    println("Sumar enteros $intExampleVar + $intExample = ${intExample + intExampleVar}")
    println("Restar enteros $intExampleVar - $intExample = ${intExample - intExampleVar}")
    println("Multiplicar enteros $intExampleVar * $intExample = ${intExample * intExampleVar}")
    println("Dividir enteros $intExampleVar / $intExample = ${intExample / intExampleVar}")
    println("Resto o modulo de enteros $intExampleVar % $intExample = ${intExample % intExampleVar}")

    //Podemos operar con enteros y decimales, pero el resultado nos lo convertira a un tipo u otro
    //para pasar de un tipo a otro podremos utilizar lo siguiente
    val resultado : Int = intExample + floatExample.toInt()

}

fun variablesBoolean(){

    //----------VARIABLES BOOLEANAS----------

    //Boolean, devolvera true o false
    val booleanExample1 : Boolean = true
    val booleanExample2 : Boolean = false

    println(booleanExample2)

}

fun variablesAlfaNumericas(){

    //----------VARIABLES ALFANUMERICAS----------

    //Char, para 1 caracter. Debe ir entre comillas simples ' '
    val charExample1 : Char = 'H'
    val charExample2 : Char = '2'
    val charExample3 : Char = '@'

    //String
    var stringExample1 : String = "Carlos"
    var stringExample2 : String = "Ventas"
    var stringExample3 : String = "Lara"

    //----------OPERACIONES CON ALFANUMERICOS----------
    //Concatenar con +
    println(stringExample1+stringExample2+stringExample3) //Lo concatenara pero no habra espacios

    //Concatenar con $
    stringExample1 = "Hola, me llamo $stringExample1 y tengo $intExample años"
    println(stringExample1)

    //Podemos pasar un string a int de la siguiente forma
    var stringIntExample : String = "123" //Es importante que para poder transformarlo solo contenga numeros
    var intStringExample : Int = stringIntExample.toInt()

    //Y lo mismo pero al reves, podemos pasar el int a string
    stringIntExample = intStringExample.toString()

}