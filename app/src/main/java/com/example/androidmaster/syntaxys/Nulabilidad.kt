package com.example.androidmaster.syntaxys

fun main(){

    //En kotlin las variables pueden ser nulas

    var name : String? = null

    //Si por ejemplo en este caso, queremos acceder a un caracter de name
    //Nos marcara como error ya que nos avisara de que ese valor es posible que sea null cuando
    //queramos acceder a el

    //Para ello, si indicamos !! detras de name, estaremos indicando al programa que cuando vaya
    //a buscar ese valor, estaremos seguros de que no va a ser null, pero si luego resulta
    //que es null, dara fallo
    println(name!!.get(3))

    //otra opcion es usar ? detras del nombre de la variable, de esta forma indicaremos un condicionante:
    //Si el valor no es nulo imprime el valor, si lo es, no hagas nada, de esta forma no dara error si el valor es nulo
    println(name?.get(3))

    //Tambien podemos seguir la condicion utilizando el operador Elvis ?:
    println(name?.get(3) ?: "El valor es nulo")
    //Aqui le estaremos diciendo, si el valor que buscamos no es nulo, imprimelo, en caso de
    //que sea nulo, imprime un mensaje

}