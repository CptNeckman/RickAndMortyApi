package com.example.rickandmorty.activity.FragmentCharacter.model

import com.google.gson.annotations.SerializedName
import retrofit2.http.Url

data class CharacterResponse(

    @SerializedName("name")
    val name: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("species")
    val species: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("location")
    val location: LocationNameResponse,
    @SerializedName("origin")
    val origin: OriginNameResponse,
    @SerializedName("episode")
    val episode: ArrayList<String>

)

