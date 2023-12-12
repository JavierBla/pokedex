package com.example.mypokedex.model

import android.app.Application

interface IObtainPokemon {
    fun obtainFromJson(pokemonName: String): Pokemon
    fun obtainFromApi()
}