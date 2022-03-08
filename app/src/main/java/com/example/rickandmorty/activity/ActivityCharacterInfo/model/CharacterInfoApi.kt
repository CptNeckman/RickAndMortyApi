package com.example.rickandmorty.activity.ActivityCharacterInfo.model

import com.example.rickandmorty.activity.FragmentCharacter.model.CharacterResponse
import com.example.rickandmorty.activity.FragmentEpisode.model.EpisodeData
import com.example.rickandmorty.activity.FragmentEpisode.model.EpisodeResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterInfoApi {

        @GET("")
        fun getData() : Single<EpisodeResponse>
    }
