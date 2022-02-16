package com.example.rickandmorty.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmorty.MainActivity
import com.example.rickandmorty.R

class CharacterAdapter(private val characterName: ArrayList<String>,
                       private val lifeStatus: ArrayList<String>,
                       private val species: ArrayList<String>,
                       private val originName: ArrayList<String>,
                       private val characterPhoto : ArrayList<String>):
    RecyclerView.Adapter<CharacterAdapter.MyViewHolder>(){

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val characterName : TextView = itemView.findViewById(R.id.character_name)
        val lifeStatus : TextView = itemView.findViewById(R.id.life_status)
        val species : TextView = itemView.findViewById(R.id.species)
        val originName : TextView = itemView.findViewById(R.id.origin_name)
        val characterImage : ImageView = itemView.findViewById(R.id.character_photo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.characterName.text = characterName[position]
        holder.lifeStatus.text = lifeStatus[position]
        holder.species.text = species[position]
        holder.originName.text = originName[position]
        Glide
            .with(MainActivity().applicationContext)
            .load(characterPhoto[position])
            .into(holder.characterImage)

    }

    override fun getItemCount(): Int {
        return characterName.size
    }


}