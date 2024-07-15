package com.example.androidmaster.syntaxys

fun main(){

    ifBasico("Carlos")
    ifAnidado("dogo")

}

fun ifMultipleOR(){

    var pet : String = "dog"
    var happy : Boolean = true

    //Con || si una de las dos devuelve true, sera true la condicion entera
    if(pet == "dog" || (pet == "cat" && happy)){

        //En este caso si pet = dog devuelve true, entra, si pet != dog, pasa a evaluar la siguiente
        //condicion, al ir en parentesis y con && la evalua de forma conjunta, es decir para que pet == "cat" && happy
        //devuelva true, ambas condiciones deben devolver true, ya que lleva un && (AND)
        println("Es una mascota")
    }else{
        println("No es una mascota")
    }

}

fun ifMultipleAND(){

    var age : Int = 18
    var parentPermission : Boolean = false
    var happy : Boolean = true

    //Para que entre, todas tienen que devolver true, en el momento que una devuelve false, no cumpliria la condicion
    if(age >= 18 && parentPermission && happy){
        println("Puede beber cerveza")
    }else{
        println("No puede beber cerveza")
    }

}

fun ifInit(){

    var age : Int = 29

    if(age >= 18){
        println("Puede beber cerveza")
    }else{
        println("Debe beber zumo")
    }

}

fun ifBoolean(){

    var happy : Boolean = true

    //Sin nada al principio == true
    //Con ! al principio == false

    if(happy){

    }

    //Podemos enviar el condicionante contrario
    /*
    * if(!happy){Se ejecutara si happy devuelve false}
    * */

}

fun ifAnidado(animal:String){

    if(animal == "dog"){
        println("Es un perro")
    }else if(animal == "cat"){
        println("Es un gato")
    }else{
        println("No es un animal")
    }

}

fun ifBasico(name:String){
    
    if(name == "Carlos"){
        println("Oye, la variable name es Carlos")
    }else{
        println("Este no es Carlos")
    }

}