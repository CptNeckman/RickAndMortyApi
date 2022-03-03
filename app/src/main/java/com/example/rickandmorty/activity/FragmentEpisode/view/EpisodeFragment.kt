package com.example.rickandmorty.activity.FragmentEpisode.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmorty.activity.FragmentCharacter.view.CharacterFragment
import com.example.rickandmorty.activity.FragmentEpisode.model.EpisodeApi
import com.example.rickandmorty.activity.FragmentEpisode.model.EpisodeData
import com.example.rickandmorty.activity.MainActivity
import com.example.rickandmorty.adapter.EpisodeAdapter
import com.example.rickandmorty.databinding.FragmentEpisodeBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class EpisodeFragment : Fragment() {

    private var _binding: FragmentEpisodeBinding? = null
    private val binding get() = _binding!!
    private val nameList = ArrayList<String>()
    private val airDateList = ArrayList<String>()
    private val episodeList = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEpisodeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        for (i in 1..51 ) {
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

        val api = retrofit.create(EpisodeApi::class.java)

        val call = api.getData(token)
            .observeOn(Schedulers.io())
            .map { data ->
                EpisodeData(
                    data.name,
                    data.airDate,
                    data.episode
                )
            }

        call.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    nameList.add(it.name)
                    airDateList.add(it.airDate)
                    episodeList.add(it.episode)
                    binding.recyclerView.adapter =
                        EpisodeAdapter(nameList, airDateList, episodeList)
                },
                {
                }
            )
    }

}