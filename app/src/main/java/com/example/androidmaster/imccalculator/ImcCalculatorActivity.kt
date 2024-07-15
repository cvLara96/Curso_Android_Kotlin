package com.example.androidmaster.imccalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.example.androidmaster.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat
import kotlin.math.pow

class ImcCalculatorActivity : AppCompatActivity() {

    private var isMaleSelected: Boolean = true
    private var isFemaleSelected: Boolean = false
    private var currentWeight: Int = 50 //Indicaremos un valor para el peso por defecto
    private var currentAge: Int = 25 //Indicaremos un valor para la edad por defecto
    private var currentHeight: Int = 120 //Indicaremos un valor para la altura por defecto

    //Con lateinit indicaremos que se iniciara despues
    //Como queremos utilizarlo en varios metodos, deberemos declararlo aqui
    //y lo inicializaremos en los diferentes metodos
    private lateinit var cardViewMale: CardView
    private lateinit var cardViewFemale: CardView
    private lateinit var tvHeight: TextView
    private lateinit var rangeHeight: RangeSlider
    private lateinit var btnSubtractWeight: FloatingActionButton
    private lateinit var btnAddWeight: FloatingActionButton
    private lateinit var tvWeight: TextView
    private lateinit var btnSubtractAge: FloatingActionButton
    private lateinit var btnAddAge: FloatingActionButton
    private lateinit var tvAge: TextView
    private lateinit var btnCalculate: Button

    //Vamos a crear un companion object (en java seria el equivalente a un static) que hara que lo que este dentro, se podra utilizar
    //por las demas actividades, en este caso, meteremos una constante que almacenara la clave del
    //intent que contiene el resultado del calculo
    companion object{
        const val IMC_KEY = "RESULT_IMC"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc_calculator)

        //Crearemos un metodo que se encargara de iniciar los componentes
        initComponents()
        //Crearemos otro metodo encargado de iniciar los listeners
        initListeners()
        //Crearemos un metodo que iniciara la Interfaz de Usuario
        initIU()

    }


    //Iniciara los componentes
    private fun initComponents() {
        cardViewMale = findViewById(R.id.cardViewMale)
        cardViewFemale = findViewById(R.id.cardViewFemale)
        tvHeight = findViewById(R.id.tvHeight)
        rangeHeight = findViewById(R.id.rangeHeight)
        btnSubtractWeight = findViewById(R.id.btnSubtractWeight)
        btnAddWeight = findViewById(R.id.btnAddWeight)
        tvWeight = findViewById(R.id.tvWeight)
        btnSubtractAge = findViewById(R.id.btnSubtractAge)
        btnAddAge = findViewById(R.id.btnAddAge)
        tvAge = findViewById(R.id.tvAge)
        btnCalculate = findViewById(R.id.btnCalculate)
    }

    //Este metodo accedera a las View y les pondra un setOnClickListener
    private fun initListeners() {
        //Cuando se pulse sobre los cardView se llamara al metodo setGenderColor()
        cardViewMale.setOnClickListener {
            if (!isMaleSelected) {
                changeGender()//Cambiara el valor de los boolean que indican que genero se ha seleccionado (solo si ya esta en false previamente)
                //Si esta en true mantendra el color
            }
            setGenderColor()
        }
        cardViewFemale.setOnClickListener {
            if (!isFemaleSelected) {
                changeGender()//Cambiara el valor de los boolean que indican que genero se ha seleccionado (solo si ya esta en false previamente)
                //Si esta en true mantendra el color
            }
            setGenderColor()
        }

        //Iniciaremos el listener para el rangeSlider
        //El metodo addOnChangeListener cada vez que se mueva el slider nos devolvera el slider, el valor y el fromUser
        //Solo necesitaremos el value, por lo que slider y fromUser podemos sustituirlo por _
        rangeHeight.addOnChangeListener { slider, value, fromUser ->

            //En este caso tendremos que formatear el decimal para que el numero que devuelva sea entero
            /**
             * Según el patrón #.##, el número 120.0 se formatea como "120"
             * (sin decimales porque .## indica que los decimales son opcionales
             * y no se muestran ceros innecesarios).
             */
            val decimalFormat = DecimalFormat("#.##")
            currentHeight = decimalFormat.format(value).toInt()
            tvHeight.text = "$currentHeight cm"

        }

        //Iniciaremos el listener para los botones de aumentar o disminuir el peso
        btnSubtractWeight.setOnClickListener {
            //Restara 1 al valor por defecto, si es el valor es 0 lanzara una tostada indicando que no puede bajar ams
            if (currentWeight == 0) {
                Toast.makeText(
                    this,
                    "El valor no puede ser inferior a $currentWeight",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                currentWeight -= 1
                setWeight()
            }

        }

        btnAddWeight.setOnClickListener {
            //Sumara 1 al valor por defecto, si es el valor es 500 lanzara una tostada
            if (currentWeight == 500) {
                Toast.makeText(
                    this,
                    "El valor no puede ser superior a $currentWeight",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                currentWeight += 1
                setWeight()

            }

        }

        //Iniciaremos los listener para los botones de aumentar o disminuir la edad
        btnAddAge.setOnClickListener {
            //Sumara 1 al valor por defecto, si es el valor es 100 lanzara una tostada
            if (currentAge == 100) {
                Toast.makeText(
                    this,
                    "El valor no puede ser superior a $currentAge",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                currentAge += 1
                setAge()

            }

        }

        btnSubtractAge.setOnClickListener {
            //Restara 1 al valor por defecto, si es el valor es 0 lanzara una tostada indicando que no puede bajar ams
            if (currentAge == 0) {
                Toast.makeText(
                    this,
                    "El valor no puede ser inferior a $currentAge",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                currentAge -= 1
                setAge()
            }
        }

        //Indicaremos el listener para el boton de calcular
        btnCalculate.setOnClickListener {
            val result = calculateIMC()
            //Crearemos un metodo que enviara el resultado a la actividad result a traves de un intent
            navigateToResult(result)

        }
    }

    //Metodo que enviara el dato de IMC a la activity result
    private fun navigateToResult(result: Double) {

        intent = Intent(this, ResultIMCActivity::class.java)
        intent.putExtra(IMC_KEY, result)
        startActivity(intent)

    }

    //Crearemos la funcion que calculara el IMC y lo devolvera
    private fun calculateIMC(): Double {
        //Crearemos un formato para sacar el imc con dos decimales
        val decimalFormat = DecimalFormat("#.##")
        //La altura la dividimos entre 100 porque currentHeight esta en centimetros
        //Para utilizar potencias importamos import kotlin.math.pow, y utilizamos el metodo .pow(n) n sera el numero al que elevamos la base
        val imc = currentWeight / ((currentHeight.toDouble() / 100).pow(2))
        return decimalFormat.format(imc).toDouble()

    }

    //Crearemos una funcion que lo que hara sera establecer el nuevo peso al textView que muestra el peso
    private fun setWeight() {
        tvWeight.text = currentWeight.toString()
    }

    //Crearemos una funcion que lo que hara sera establecer la edad al textview que muestra la edad
    private fun setAge() {
        tvAge.text = currentAge.toString()
    }

    //Esta funcion se encargara de cambiar el valor del boolean de los generos
    private fun changeGender() {
        //Lo que hara sera cambiar el valor de las variables a lo contrario
        //De manera que si una esta en false, pasara a true y si esta en true pasara a false
        isMaleSelected = !isMaleSelected
        isFemaleSelected = !isFemaleSelected

    }

    //Crearemos una funcion para cuando pulsemos en los cardView de Male o Female
    //que se encargara de cambiar el color de fondo del cardview
    private fun setGenderColor() {
        //Para cambiar el color de fondo del cardView seleccionado utilizamos setCardBackgroundColor()
        //dentro de setCardBackgroundColor() llamaremos al metodo getBackGroundColor()
        cardViewMale.setCardBackgroundColor(getBackgroundColor(isMaleSelected))
        cardViewFemale.setCardBackgroundColor(getBackgroundColor(isFemaleSelected))
    }

    //Crearemos una funcion que se encargara de cambiar el color del cardview dependiendo
    //del boolean que reciba como parametro (estos boolean que recibe como parametro se declaran al principio de
    //la clase) y cambiaran su valor en el metodo changeGender dependiendo de cual se pulse, es decir,
    //si se pulsa sobre male, se pondra en true y female en false, y viceversa
    private fun getBackgroundColor(isSelectedComponent: Boolean): Int {

        //Ahora filtrara, y si recibe como parametro un true, pondra un color, si recibe false, pondra otro
        val realColor = if (isSelectedComponent) {
            R.color.background_component_selected
        } else {
            R.color.background_component
        }

        //Para acceder al color tendremos que hacer uso de ContextCompat, pasandole como parametro el valor que haya asignado
        //a la variable realColor segun el boolean recibido

        return ContextCompat.getColor(this, realColor)//Y devolvera el color con return

    }

    //Este metodo iniciara la interfaz de usuario con una interfaz por defecto
    private fun initIU() {

        //setGenderColor establecera por defecto un color a cada genero
        setGenderColor()
        //setWeight establecera por defecto el valor que le hemos dado a currentWeight
        setWeight()
        //setAge establecera por defecto el valor que le hemos dado a currentAge
        setAge()

    }


}