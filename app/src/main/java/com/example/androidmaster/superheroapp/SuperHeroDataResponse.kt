package com.example.androidmaster.superheroapp

import com.google.gson.annotations.SerializedName
//5(retrofit y corrutinas)
//Los atributos que recuperara son los que indica el archivo JSON de la API:
//Lo que nos interesa son los results
/**
 * response
 * result-for
 * results
 */
data class SuperHeroDataResponse(
    @SerializedName("response") val response : String,
    @SerializedName("results") val results : List<SuperHeroItemResponse>
)
/**
 * Nota de los JSON:
 *  SI TIENE {} ES UN OBJETO
 *  SI TIENE [] ES UN LIST
 */

//Para la lista de resultados, sera necesario crear una clase con los atributos que en este caso tienen los elementos:
/*
* "id": "63",
* "name": "Batgirl"
* */
data class SuperHeroItemResponse(
    @SerializedName("id") val superHeroID : String,
    @SerializedName("name") val superHeroName : String,
    @SerializedName("image") val superHeroImage : SuperHeroImageResponse

)

//Para acceder a la imagen, deberemos creear otra dataClass ya que esta dentro de image{}
/*
"image": {
  "url": "https://www.superherodb.com/pictures2/portraits/10/100/1111.jpg"
},
 */
data class SuperHeroImageResponse(
    @SerializedName("url") val url : String
)
