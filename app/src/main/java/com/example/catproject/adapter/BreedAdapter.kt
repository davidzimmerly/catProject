package com.example.catproject.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.catproject.R
import com.example.catproject.retrofit.model.Breed
import kotlinx.android.synthetic.main.breed_item_view.view.*

class BreedAdapter(private val context: Context, private var breeds:List<Breed>) : RecyclerView.Adapter<BreedAdapter.BreedViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        BreedViewHolder(LayoutInflater.from(context).inflate(R.layout.breed_item_view, parent, false))


    fun setData(breeds : List<Breed>){
        this.breeds = breeds
        notifyItemRangeChanged(0, breeds.size)

    }

    override fun getItemCount(): Int {
        return breeds.size
    }

    override fun onBindViewHolder(holder: BreedViewHolder, position: Int) {
        val breed:Breed = breeds[position]
        holder.itemView.tvBreedName.text = breed.name
        holder.itemView.tvDesc.text = breed.description
        /*holder.itemView.tvBreedTemp.text = "Temperament = ${breed.temperament}"
        holder.itemView.tvBreedOrigin.text = "Origin = ${breed.origin}"*/
    }

    class BreedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


}