package com.example.rickandmorty.activity.FragmentEpisode.model

import com.example.rickandmorty.activity.FragmentCharacter.model.CharacterResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface EpisodeApi {

    @GET("api/episode/{token}")
    fun getData(@Path("token") token: Int): Single<EpisodeResponse>
}