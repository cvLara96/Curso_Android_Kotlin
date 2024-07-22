package com.example.androidmaster.superheroapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import com.example.androidmaster.R
import com.example.androidmaster.databinding.ActivityDetailSuperHeroBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

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
                val response : SuperHeroDetailResponse? = superHeroDetail.body()

                if(response != null){
                    //Ahora sobre el hilo principal:
                    runOnUiThread{
                        createUI(response)
                    }
                }

            }else{
                Log.i("CarlosVL", "no funciona")
            }

        }

    }

    //Este metodo creara la IU con la info del super Heroe
    private fun createUI(response: SuperHeroDetailResponse) {




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