package com.example.rickandmorty.activity.FragmentLocation.model

import androidx.annotation.Dimension
import com.google.gson.annotations.SerializedName

data class LocationResponse(

    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("dimension")
    val dimension: String
)
