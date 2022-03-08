package com.example.rickandmorty.activity.FragmentCharacter.model

import retrofit2.http.Url

data class CharacterData(

    val name: String,
    val status: String,
    val species: String,
    val image: String,
    val locationName: String,
    val originName: String,
    val episode: ArrayList<String>
)
