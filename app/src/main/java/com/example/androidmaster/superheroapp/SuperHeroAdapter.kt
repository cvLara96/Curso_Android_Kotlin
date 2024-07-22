package com.example.androidmaster.superheroapp

import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidmaster.R

//Este adapter recibira como parametro una lista de objetos SuperHeroItemResponse, ya que esa clase contiene la info de los super heroes
class SuperHeroAdapter(
    var superHeroList: List<SuperHeroItemResponse> = emptyList(),
    private val navigateToDetail: (String) -> Unit,
) :
    RecyclerView.Adapter<SuperHeroViewHolder>() {

    //Crearemos un metodo que actualizara la lista que se envia como parametro
    fun updateList(superHeroList: List<SuperHeroItemResponse>) {
        this.superHeroList = superHeroList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {

        return SuperHeroViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_superhero, parent, false)
        )

    }

    override fun onBindViewHolder(holder: SuperHeroViewHolder, position: Int) {

        //Enviamos tambien la funcion lambda
        holder.bind(superHeroList[position], navigateToDetail)

    }

    override fun getItemCount() = superHeroList.size
}