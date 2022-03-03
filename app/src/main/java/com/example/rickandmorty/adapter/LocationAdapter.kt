package com.example.rickandmorty.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R

class LocationAdapter (
    private val nameList: ArrayList<String>,
    private val typeList: ArrayList<String>,
    private val dimensionList: ArrayList<String>,
    ) :
    RecyclerView.Adapter<LocationAdapter.MyViewHolder>() {

        class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            val name: TextView = itemView.findViewById(R.id.locationName)
            val type: TextView = itemView.findViewById(R.id.typeLocation)
            val dimension: TextView = itemView.findViewById(R.id.dimension)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val itemView =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.location_recyclerview_item, parent, false)
            return MyViewHolder(itemView)
        }


        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.name.text = nameList[position]
            holder.type.text = typeList[position]
            holder.dimension.text = dimensionList[position]
        }

        override fun getItemCount(): Int {
            return nameList.size
        }

    }
