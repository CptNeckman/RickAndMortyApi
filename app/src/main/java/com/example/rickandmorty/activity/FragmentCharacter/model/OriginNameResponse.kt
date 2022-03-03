package com.example.rickandmorty.activity.FragmentCharacter.model

import com.google.gson.annotations.SerializedName

data class OriginNameResponse(

    @SerializedName("name")
    val originName: String
)