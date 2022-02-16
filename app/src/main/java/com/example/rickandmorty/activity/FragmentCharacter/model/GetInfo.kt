package com.example.rickandmorty.activity.FragmentCharacter.model

import android.widget.Toast
import com.example.rickandmorty.activity.FragmentCharacter.view.CharacterFragment
import com.example.rickandmorty.adapter.CharacterAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class GetInfo {

    private val nameList = ArrayList<String>()
    private val statusList = ArrayList<String>()
    private val speciesList = ArrayList<String>()
    private val genderList = ArrayList<String>()
    private val originList = ArrayList<String>()
    private val locationList = ArrayList<String>()
    private val imageList = ArrayList<String>()

    fun getInfo(token: Int) {

        val okHttp = OkHttpClient.Builder()
            .readTimeout(
                10,
                TimeUnit.SECONDS
            )
            .connectTimeout(
                10,
                TimeUnit.SECONDS
            )
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/")
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

        val api = retrofit.create(Api::class.java)

        val call = api.getData(token)
            .observeOn(Schedulers.io())
            .map { data ->
                CharacterData(
                    data.name,
                    data.status,
                    data.species
                )
            }

        call.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    nameList.add(it.name)
                    statusList.add(it.status)
                    speciesList.add(it.species)


                },
                { error ->
                    Toast.makeText(CharacterFragment().context, error.toString(), Toast.LENGTH_LONG).show()
                }
            )
    }
}

