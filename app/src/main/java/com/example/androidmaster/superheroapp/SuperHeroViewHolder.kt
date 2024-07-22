package com.example.androidmaster.superheroapp

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.androidmaster.databinding.ItemSuperheroBinding
import com.squareup.picasso.Picasso

class SuperHeroViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    //Podemos crear el binding aqui
    private val binding = ItemSuperheroBinding.bind(view)

    //Este metodo recibira la funcion lambda
    fun bind(superHeroItemResponse: SuperHeroItemResponse, navigateToDetail: (String) -> Unit) {

        binding.tvSuperHeroName.text = superHeroItemResponse.superHeroName

        //Para cargar la imagen utilizaremos la libreria Picasso
        Picasso.get().load(superHeroItemResponse.superHeroImage.url).into(binding.ivSuperHero);

        //Con root hacemos referencia a toda la vista
        binding.root.setOnClickListener { navigateToDetail(superHeroItemResponse.superHeroID) }

    }

}