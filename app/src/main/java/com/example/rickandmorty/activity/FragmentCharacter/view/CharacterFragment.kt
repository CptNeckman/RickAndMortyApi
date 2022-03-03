package com.example.rickandmorty.activity.FragmentCharacter.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmorty.activity.FragmentCharacter.model.CharacterApi
import com.example.rickandmorty.activity.FragmentCharacter.model.CharacterData
import com.example.rickandmorty.adapter.CharacterAdapter
import com.example.rickandmorty.databinding.FragmentCharacterBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class CharacterFragment : Fragment() {

    private var _binding: FragmentCharacterBinding? = null
    private val binding get() = _binding!!
    private var textus = ArrayList<String>()
    private val statusList = ArrayList<String>()
    private val speciesList = ArrayList<String>()
    private val imageList = ArrayList<String>()
    private val locationList = ArrayList<String>()
    private val originList = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        for (i in 1..100) {
            characterGetInfo(i)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun characterGetInfo(token: Int) {

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

        val api = retrofit.create(CharacterApi::class.java)

        val call = api.getData(token)
            .observeOn(Schedulers.io())
            .map { data ->
                CharacterData(
                    data.name,
                    data.status,
                    data.species,
                    data.image,
                    data.location.locationName,
                    data.origin.originName
                )
            }

        call.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    textus.add(it.name)
                    statusList.add(it.status)
                    speciesList.add(it.species)
                    imageList.add(it.image)
                    locationList.add(it.locationName)
                    originList.add(it.originName)
                    binding.recyclerView.adapter = CharacterAdapter(
                        textus,
                        statusList,
                        speciesList,
                        imageList,
                        locationList,
                        originList
                    )
                },
                { error ->
                    Toast.makeText(CharacterFragment().context, error.toString(), Toast.LENGTH_LONG)
                        .show()
                }
            )
    }
}