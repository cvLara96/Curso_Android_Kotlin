package com.example.androidmaster.superheroapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.androidmaster.R
import com.example.androidmaster.databinding.ActivitySuperHeroListBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class SuperHeroListActivity : AppCompatActivity() {

    //Creamos el binding
    private lateinit var binding: ActivitySuperHeroListBinding

    //2(retrofit y corrutinas) Creamos el objeto Retrofit
    private lateinit var retrofit: Retrofit

    //Creamos los componentes
    private lateinit var searchView: SearchView
    private lateinit var rvSuperHero: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuperHeroListBinding.inflate(layoutInflater)//Binding
        setContentView(binding.root)//Binding

        //3(retrofit y corrutinas) Inicializamos el objeto retrofit
        retrofit = getRetrofit()

        //Inicializamos la interfaz de usuario
        initIU()


    }

    //Metodo que inicia la interfaz de usuario
    private fun initIU() {
        //Utilizamos binding para encontrar el searchView
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            //El uso de OnQueryTextListener nos hara de implementar estos dos metodos

            //Esta funcion se llamara cuando pulsemos el boton de buscar
            override fun onQueryTextSubmit(query: String?): Boolean {
                //Llamaremos a la funcion searchByName pasandole como parametro la query 
                searchByName(query.orEmpty())//Con orEmpty() indicamos que si query es null pase un texto vacio
                return false
            }

            //Esta funcion se ira llamando cuando el texto vaya cambiando
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

    }

    private fun searchByName(query: String) {

        //Mostraremos el progressbar
        binding.progressBar.isVisible = true //Si esta en true se muestra, si no, se pone en GONE

        //6(retrofit y corrutinas)
        //Cuando busquemos, retrofit hara la llamada a la API
        //Lo que haremos primero sera lanzar una corrutina en un hilo secundario (IO),
        //de manera que lo que indiquemos dentro del launch{} se va a ejecutar en un hilo secundario
        CoroutineScope(Dispatchers.IO).launch {
            //Ahora creamos una variable y la igualamos llamando al metodo create de retrofit y llamando a la interfaz
            //y a su vez a la funcion getSuperHeroes() que recibira por parametro la query
            val myResponse: Response<SuperHeroDataResponse> = retrofit.create(ApiService::class.java).getSuperHeroes(query)

            //Comprobamos si la peticion ha funcionado
            if(myResponse.isSuccessful){
                //Accedemos al contenido de la response (especificado en la dataClass)
                //Queremos acceder a los results
                val response : SuperHeroDataResponse? = myResponse.body()
                if(response != null){
                    Log.i("CarlosVL", response.toString())
                    //Para que deje de mostrar la progressBar cuando lo encuentra, al estar dentro de una corrutina,
                    //debemos indicarle que lo haga fuera de esta, utilizando runOnUiThread{}
                    //Esto ejecutara en el hilo principal lo que vaya entre sus llaves {}
                    runOnUiThread{
                        binding.progressBar.isVisible = false
                    }

                }

            }else{
                Log.i("CarlosVL", "no funciona")
            }

        }

    }

    //1(retrofit y corrutinas) Para usar retrofit hay que crear un objeto de retrofit
    //en el manifest hay que dar permisos
    private fun getRetrofit() : Retrofit{
       return Retrofit
            .Builder()
            .baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

}


