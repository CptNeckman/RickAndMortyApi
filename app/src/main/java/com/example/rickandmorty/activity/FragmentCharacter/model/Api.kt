package com.example.rickandmorty.activity.FragmentCharacter.model

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("api/character")
    fun getData(@Query("token") token: Int) : Single<CharacterResponse>
}