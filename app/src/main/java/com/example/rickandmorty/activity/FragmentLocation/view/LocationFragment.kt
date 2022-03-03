package com.example.rickandmorty.activity.FragmentLocation.view

import android.location.Location
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmorty.R
import com.example.rickandmorty.activity.FragmentEpisode.model.EpisodeApi
import com.example.rickandmorty.activity.FragmentEpisode.model.EpisodeData
import com.example.rickandmorty.activity.FragmentLocation.model.LocationApi
import com.example.rickandmorty.activity.FragmentLocation.model.LocationData
import com.example.rickandmorty.adapter.EpisodeAdapter
import com.example.rickandmorty.adapter.LocationAdapter
import com.example.rickandmorty.databinding.FragmentEpisodeBinding
import com.example.rickandmorty.databinding.FragmentLocationBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class LocationFragment: Fragment() {

    private var _binding: FragmentLocationBinding? = null
    private val binding get() = _binding!!
    private val nameList = ArrayList<String>()
    private val typeList = ArrayList<String>()
    private val dimensionList = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLocationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        for (i in 1..126 ) {
            episodeGetInfo(i)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun episodeGetInfo(token: Int) {

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

        val api = retrofit.create(LocationApi::class.java)

        val call = api.getData(token)
            .observeOn(Schedulers.io())
            .map { data ->
                LocationData(
                    data.name,
                    data.type,
                    data.dimension
                )
            }

        call.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    nameList.add(it.name)
                    typeList.add(it.type)
                    dimensionList.add(it.dimension)
                    binding.recyclerView.adapter =
                        LocationAdapter(nameList, typeList, dimensionList)
                },
                {
                }
            )
    }

}