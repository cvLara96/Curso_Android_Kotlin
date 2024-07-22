package com.example.androidmaster.superheroapp

import com.google.gson.annotations.SerializedName

data class SuperHeroDetailResponse(

    @SerializedName("name") val name: String,
    @SerializedName("powerstats") val superHeroStats : SuperHeroStats,
    @SerializedName("image") val image : SuperHeroDetailImageResponse,
    @SerializedName("biography") val biography: Biography
)

data class SuperHeroStats(

    @SerializedName("intelligence") val intelligence : String,
    @SerializedName("strength") val strength : String,
    @SerializedName("speed") val speed : String,
    @SerializedName("durability") val durability : String,
    @SerializedName("power") val power : String,
    @SerializedName("combat") val combat : String

    /*
    Como son entre comillas son Strings
    "intelligence": "100",
    "strength": "26",
    "speed": "27",
    "durability": "50",
    "power": "47",
    "combat": "100",
     */
)

//Para extraer la imagen:
data class SuperHeroDetailImageResponse(
    @SerializedName("url") val url : String
)

//Para la informacion de la biografia
data class Biography(
    @SerializedName("full-name") val full_name : String,
    @SerializedName("publisher") val publisher : String
)

