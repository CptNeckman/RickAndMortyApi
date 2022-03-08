package com.example.rickandmorty.activity.ActivityCharacterInfo.view

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmorty.R
import com.example.rickandmorty.activity.ActivityCharacterInfo.model.CharacterInfoApi
import com.example.rickandmorty.activity.ActivityCharacterInfo.model.CharacterInfoData
import com.example.rickandmorty.activity.FragmentCharacter.model.CharacterApi
import com.example.rickandmorty.activity.FragmentCharacter.model.CharacterData
import com.example.rickandmorty.activity.FragmentCharacter.view.CharacterFragment
import com.example.rickandmorty.activity.FragmentEpisode.model.EpisodeData
import com.example.rickandmorty.adapter.CharacterAdapter
import com.example.rickandmorty.adapter.EpisodeAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.OkHttpClient
import org.w3c.dom.Text
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Url
import java.util.concurrent.TimeUnit

class ActivityCharacterInfo : AppCompatActivity() {

    lateinit var characterName: TextView
    lateinit var characterStatus: TextView
    lateinit var characterSpecies: TextView
    lateinit var characterOrigin: TextView
    lateinit var characterLastLocation: TextView
    lateinit var characterPhoto: ImageView
    lateinit var recyclerView: RecyclerView
    private val nameList = ArrayList<String>()
    private val airDateList = ArrayList<String>()
    private val episodeList = ArrayList<String>()
    var episode = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity_character)

        characterName = findViewById(R.id.characterName)
        characterStatus = findViewById(R.id.characterStatus)
        characterSpecies = findViewById(R.id.characterSpecies)
        characterOrigin = findViewById(R.id.characterOrigin)
        characterLastLocation = findViewById(R.id.characterLastLocation)
        characterPhoto = findViewById(R.id.characterPhoto)

        characterName.text = intent.extras?.getString("characterName")
        characterStatus.text = intent.extras?.getString("lifeStatus")
        characterSpecies.text = intent.extras?.getString("species")
        characterOrigin.text = intent.extras?.getString("originName")
        characterLastLocation.text = intent.extras?.getString("locationName")
        val photo = intent.extras?.getString("characterPhoto")
        Glide
            .with(this)
            .load(photo)
            .placeholder(R.drawable.default_picture)
            .into(characterPhoto)


    }

    private fun characterGetInfo(token: String) {

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
            .baseUrl(token)
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

        val api = retrofit.create(CharacterInfoApi::class.java)

        val call = api.getData()
            .observeOn(Schedulers.io())
            .map { data ->
                CharacterInfoData(
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

                },
                { error ->
                    Toast.makeText(CharacterFragment().context, error.toString(), Toast.LENGTH_LONG)
                        .show()
                }
            )
    }
}