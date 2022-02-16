package com.example.rickandmorty.activity.FragmentCharacter.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.MainActivity
import com.example.rickandmorty.R
import com.example.rickandmorty.activity.FragmentCharacter.model.GetInfo
import com.example.rickandmorty.adapter.CharacterAdapter

class CharacterFragment : Fragment() {

    val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerview_character)


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_character, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val x = arrayListOf("Rick")
        val y = arrayListOf("Alive")
        val z = arrayListOf("Human")

        recyclerView?.layoutManager = LinearLayoutManager(MainActivity().applicationContext)
        recyclerView?.adapter = CharacterAdapter(x,y,z)
    }

}