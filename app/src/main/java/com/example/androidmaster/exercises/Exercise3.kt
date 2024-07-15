package com.example.androidmaster.exercises

/**
 * En el mundo, se usan tres escalas de temperatura principales: Celsius, Fahrenheit y Kelvin.
 *
 * En el código inicial que se proporciona en el siguiente fragmento de código, escribe un programa que convierta una temperatura de una escala a otra con estas fórmulas:
 *
 * De grados Celsius a Fahrenheit: °F = 9/5 (°C) + 32
 * Kelvin a Celsius: °C = K - 273.15
 * De Fahrenheit a Kelvin: K = 5/9 (°F - 32) + 273.15
 * Ten en cuenta que el método String.format("%.2f", /* measurement */ ) se usa para convertir un número en un tipo String con 2 decimales.
 *
 * RESULTADO:
 *
 * 27.0 degrees Celsius is 80.60 degrees Fahrenheit.
 * 350.0 degrees Kelvin is 76.85 degrees Celsius.
 * 10.0 degrees Fahrenheit is 260.93 degrees Kelvin.
 *
 */

fun main() {

    val tempCelsius : Double = 27.0
    val tempFarenheit : Double = 10.0
    val tempKelvin : Double = 350.0

    val unitCelsius : String = "Celsius"
    val unitFarenheit : String = "Farenheit"
    val unitKelvin : String = "Kelvin"

    printFinalTemperature(tempCelsius, unitCelsius, unitFarenheit, ::Celsius_Farenheit) //Para pasar una funcion como referencia usamos ::
    printFinalTemperature(tempKelvin, unitKelvin, unitCelsius, ::Kelvin_Celsius) //Para pasar una funcion como referencia usamos ::
    printFinalTemperature(tempFarenheit, unitFarenheit, unitKelvin, ::Farenheit_Kelvin) //Para pasar una funcion como referencia usamos ::

}

fun printFinalTemperature(
    initialMeasurement: Double,
    initialUnit: String,
    finalUnit: String,
    conversionFormula: (Double) -> Double //Funcion que recibe un double como parametro y devuelve un double
) {
    val finalMeasurement = String.format("%.2f", conversionFormula(initialMeasurement)) // two decimal places
    println("$initialMeasurement degrees $initialUnit is $finalMeasurement degrees $finalUnit.")
}


fun Celsius_Farenheit(temp : Double) : Double{

    val temFinal : Double = (9 * temp)/5 + 32
    return temFinal

}

fun Kelvin_Celsius(temp : Double) : Double{

    val tempFinal : Double = temp - 273.15
    return tempFinal

}

fun Farenheit_Kelvin(temp : Double) : Double{

    val tempFinal : Double = (5* (temp - 32))/9 + 273.15
    return  tempFinal

}

