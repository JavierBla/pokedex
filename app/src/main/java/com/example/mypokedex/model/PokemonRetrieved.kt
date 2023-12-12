package com.example.mypokedex.model

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder

class PokemonRetrieved(application: Application): IObtainPokemon {

    private val context: Context = application.applicationContext

    override fun obtainFromJson(pokemonName: String): Pokemon {
        val jsonFile = context.assets.open("$pokemonName.json").bufferedReader().use { it.readText() }
        val gson: Gson = GsonBuilder().registerTypeAdapter(Pokemon::class.java,PokemonDeserialized()).create()
        val pokemonJson = gson.fromJson(jsonFile,Pokemon::class.java)

        return pokemonJson
    }

    override fun obtainFromApi() {
        TODO("Not yet implemented")
    }
}