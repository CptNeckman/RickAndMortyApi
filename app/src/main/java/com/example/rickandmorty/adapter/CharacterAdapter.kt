package com.example.rickandmorty.adapter


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmorty.R
import com.example.rickandmorty.activity.ActivityCharacterInfo.view.ActivityCharacterInfo
import com.example.rickandmorty.activity.FragmentCharacter.view.CharacterFragment
import com.example.rickandmorty.activity.MainActivity

class CharacterAdapter(
    private var mContext: Context,
    private val characterName: ArrayList<String>,
    private val lifeStatus: ArrayList<String>,
    private val species: ArrayList<String>,
    private val characterPhoto: ArrayList<String>,
    private val locationName: ArrayList<String>,
    private val originName: ArrayList<String>
) :
    RecyclerView.Adapter<CharacterAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val characterName: TextView = itemView.findViewById(R.id.character_name)
        val lifeStatus: TextView = itemView.findViewById(R.id.life_status)
        val species: TextView = itemView.findViewById(R.id.species)
        val photo: ImageView = itemView.findViewById(R.id.character_photo)
        val location: TextView = itemView.findViewById(R.id.lastLocation)
        val origin: TextView = itemView.findViewById(R.id.originLocation)
        val lifeStatusIdentifier: ImageView = itemView.findViewById(R.id.life_status_identifier)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.character_recyclerview_item, parent, false)
        return MyViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.characterName.text = characterName[position]
        holder.lifeStatus.text = lifeStatus[position]
        when {
            lifeStatus[position] == "Dead" -> {
                holder.lifeStatusIdentifier.setBackgroundResource(R.drawable.background_identifier_dead)
            }
            lifeStatus[position] == "Alive" -> {
                holder.lifeStatusIdentifier.setBackgroundResource(R.drawable.background_identifier_alive)
            }
            lifeStatus[position] == "unknown" -> {
                holder.lifeStatusIdentifier.setBackgroundResource(R.drawable.background_identifier_unknown)
            }
        }
        holder.location.text = locationName[position]
        holder.origin.text = originName[position]
        holder.species.text = species[position]
        Glide
            .with(holder.itemView.context)
            .load(characterPhoto[position])
            .placeholder(R.drawable.default_picture)
            .into(holder.photo)

        holder.itemView.setOnClickListener {
            val intent = Intent(mContext, ActivityCharacterInfo::class.java)
            intent.putExtra("characterName", characterName[position])
            intent.putExtra("lifeStatus", lifeStatus[position])
            intent.putExtra("species", species[position])
            intent.putExtra("characterPhoto", characterPhoto[position])
            intent.putExtra("locationName", locationName[position])
            intent.putExtra("originName", originName[position])
            mContext.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return characterName.size
    }


}