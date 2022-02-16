package com.example.rickandmorty.activity.FragmentCharacter.model

import com.google.gson.annotations.SerializedName

data class CharacterResponse(

    @SerializedName("name")
    val name: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("species")
    val species: String,
    @SerializedName("name")
    val gender: String,
    @SerializedName("origin")
    val origin: CharacterOrigin,
    @SerializedName("location")
    val location: CharacterLocation,
    @SerializedName("image")
    val image: String

)

