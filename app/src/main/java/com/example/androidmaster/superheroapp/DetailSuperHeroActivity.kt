package com.example.androidmaster.superheroapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import androidx.core.view.isVisible
import com.example.androidmaster.R
import com.example.androidmaster.databinding.ActivityDetailSuperHeroBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import kotlin.math.roundToInt

//Esta activity se mostrara cuando pinchemos en algun superHero y nos dara mas informacion sobre el/ella
class DetailSuperHeroActivity : AppCompatActivity() {

    //Creamos el binding
    private lateinit var binding : ActivityDetailSuperHeroBinding

    //Clave del intent
    companion object{
        const val ID_KEY = "ID_SUPERHERO"
    }

    //Creamos el objeto Retrofit
    private lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSuperHeroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Con orEmpty indicamos que si el valor es nulo nos devuelva una cadena de texto vacia
        val id : String = intent.getStringExtra(ID_KEY).orEmpty()

        //Inicializamos el objeto retrofit
        retrofit = getRetrofit()
        
        //Llamamos al metodo que nos dara la info del super heroe
        getSuperHeroInformation(id)
        
    }

    private fun getSuperHeroInformation(id: String) {

        CoroutineScope(Dispatchers.IO).launch {
            //Debemos crear otro metodo en la interfaz que busque por id
            val superHeroDetail : Response<SuperHeroDetailResponse> = retrofit.create(ApiService::class.java).getSupeheroDetail(id)

            //Comprobamos si la respuesta es correcta
            if(superHeroDetail.isSuccessful){

                //Accedemos al contenido de la response (especificado en la dataClass)
                //Queremos acceder a los results
                val superHero : SuperHeroDetailResponse? = superHeroDetail.body()

                if(superHero != null){
                    //Ahora sobre el hilo principal:
                    runOnUiThread{
                        createUI(superHero)
                    }
                }

            }else{
                Log.i("CarlosVL", "no funciona")
            }

        }

    }

    //Este metodo creara la IU con la info del super Heroe
    private fun createUI(superHero: SuperHeroDetailResponse) {

        Picasso.get().load(superHero.image.url).into(binding.ivSuperHeroPhoto)
        binding.tvSHName.text = superHero.name
        prepareStats(superHero.superHeroStats)
        binding.tvRealName.text = superHero.biography.full_name
        binding.tvPublisher.text = superHero.biography.publisher

    }

    private fun prepareStats(superHeroStats: SuperHeroStats) {

        //la idea es darle a los view la altura en dp igual al valor del stat del super heroe
        //Para ello debemos acceder a sus parametros
        updateHeight(binding.viewItelligence, superHeroStats.intelligence)
        updateHeight(binding.viewStrength, superHeroStats.strength)
        updateHeight(binding.viewSpeed, superHeroStats.speed)
        updateHeight(binding.viewDurability, superHeroStats.durability)
        updateHeight(binding.viewCombat, superHeroStats.combat)
        updateHeight(binding.viewPower, superHeroStats.power)

    }

    //Creamos un metodo que actualiza la altura del componente
    private fun updateHeight(view: View, superHeroStat:String){

        //De esta forma nos evitamos repetir 6 veces este fragmento de codigo en el metodo prepareStats()
        /*
        val params = binding.viewItelligence.layoutParams
        params.height = superHeroStats.intelligence.toInt()
        binding.viewItelligence.layoutParams = params
         */

        val params = view.layoutParams
        params.height = pxToDp(superHeroStat.toFloat())
        view.layoutParams = params

    }

    //Creamos un metodo que transforme a dp lo que le pasemos
    private fun pxToDp(px:Float):Int{

        //Para transformar float a dp
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, resources.displayMetrics).roundToInt()
    }

    //Necesitaremos el retrofit
    private fun getRetrofit() : Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}