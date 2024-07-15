package com.example.androidmaster.imccalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.example.androidmaster.R
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat

class ImcCalculatorActivity : AppCompatActivity() {

    private var isMaleSelected: Boolean = true
    private var isFemaleSelected: Boolean = false

    //Con lateinit indicaremos que se iniciara despues
    //Como queremos utilizarlo en varios metodos, deberemos declararlo aqui
    //y lo inicializaremos en los diferentes metodos
    private lateinit var cardViewMale: CardView
    private lateinit var cardViewFemale: CardView
    private lateinit var tvHeight: TextView
    private lateinit var rangeHeight: RangeSlider

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
            val decimalFormat = DecimalFormat("#.##")
            val result = decimalFormat.format(value)
            tvHeight.text = "$result cm"

        }
    }

    //Esta funcion se encargara
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

        //Para ello llamamos a setGenderColor,
        setGenderColor()


    }


}