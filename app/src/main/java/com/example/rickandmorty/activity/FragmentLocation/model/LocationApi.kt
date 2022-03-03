package com.example.rickandmorty.activity.FragmentLocation.model

import com.example.rickandmorty.activity.FragmentEpisode.model.EpisodeResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface LocationApi {

    @GET("api/location/{token}")
    fun getData(@Path("token") token: Int) : Single<LocationResponse>
}