package com.example.rickandmorty.activity.FragmentCharacter.model

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterApi {

    @GET("api/character/{token}")
    fun getData(@Path("token") token: Int) : Single<CharacterResponse>
}