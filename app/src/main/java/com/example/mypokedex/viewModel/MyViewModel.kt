package com.example.mypokedex.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mypokedex.model.Pokemon
import com.example.mypokedex.model.PokemonRetrieved
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MyViewModel(application: Application): AndroidViewModel(application) {

    private val _pokemon: MutableLiveData<Pokemon> = MutableLiveData(null)
    val pokemon: LiveData<Pokemon> = _pokemon

    private val pokemonGot: PokemonRetrieved = PokemonRetrieved(application)

    init {
        loadPokemon()
    }

    private fun loadPokemon() {
        viewModelScope.launch {
            val pokemonLoaded = withContext(Dispatchers.IO) {
                pokemonGot.obtainFromJson("reshiram")
            }
            _pokemon.postValue(pokemonLoaded)
        }
    }
}