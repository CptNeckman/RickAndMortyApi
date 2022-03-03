package com.example.rickandmorty.activity.FragmentEpisode.model

import com.google.gson.annotations.SerializedName

data class EpisodeResponse(

    @SerializedName("name")
    val name: String,
    @SerializedName("air_date")
    val airDate: String,
    @SerializedName("episode")
    val episode: String
)