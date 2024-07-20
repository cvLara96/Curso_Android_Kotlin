package com.example.androidmaster.superheroapp


import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
//4(retrofit y corrutinas)
interface ApiService {

    //Aqui vamos a utilizar CORRUTINAS, para ello la llamada a la funcion debe empezar con suspend
    //Las corrutinas son llamadas que se hacen de forma asincrona, es decir no van en el hilo principal
    @GET("/api/1a12417a5d1b356feb2abe08bcd3f7ba/search/{name}")
    suspend fun getSuperHeroes(@Path("name")superHeroName : String) : Response<SuperHeroDataResponse>
    //Con (@Path("name")superHeroName : String) lo que hara sera que lo que pasemos por parametro lo susituira en la parte {name}
    //de la url "/api/1a12417a5d1b356feb2abe08bcd3f7ba/search/{name}"

    //Crearemos una dataClass para el Response

}