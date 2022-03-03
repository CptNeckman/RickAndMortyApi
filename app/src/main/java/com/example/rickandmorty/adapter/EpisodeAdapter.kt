package com.example.rickandmorty.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmorty.R

class EpisodeAdapter(
    private val nameList: ArrayList<String>,
    private val airDateList: ArrayList<String>,
    private val episodeList: ArrayList<String>,
) :
    RecyclerView.Adapter<EpisodeAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val name: TextView = itemView.findViewById(R.id.episodeName)
        val airDate: TextView = itemView.findViewById(R.id.airDate)
        val episode: TextView = itemView.findViewById(R.id.episodeNumber)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.episode_recyclerview_item, parent, false)
        return MyViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.name.text = nameList[position]
        holder.airDate.text = airDateList[position]
        holder.episode.text = episodeList[position]
    }

    override fun getItemCount(): Int {
        return nameList.size
    }

}