package com.example.rickandmorty.activity.FragmentCharacter.model

import com.google.gson.annotations.SerializedName

data class LocationNameResponse(

    @SerializedName("name")
    val locationName: String
)