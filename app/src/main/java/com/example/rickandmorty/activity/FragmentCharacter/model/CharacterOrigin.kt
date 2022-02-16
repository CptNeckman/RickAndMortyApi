package com.example.rickandmorty.activity.FragmentCharacter.model

import com.google.gson.annotations.SerializedName

data class CharacterOrigin(

    @SerializedName("name")
    val originName: String
)
