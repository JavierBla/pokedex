package com.example.mypokedex

import android.annotation.SuppressLint
import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import com.example.mypokedex.model.Pokemon
import com.example.mypokedex.ui.theme.MyPokedexTheme
import com.example.mypokedex.ui.theme.PokedexColor
import com.example.mypokedex.view.PokemonItem
import com.example.mypokedex.viewModel.MyViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyPokedexTheme {
                //val viewModel: MyViewModel by viewModels()
                MyScaffold(application)
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun MyScaffold(application: Application) {
    val myViewModel = MyViewModel(application)
    val pokemon = myViewModel.pokemon.observeAsState().value

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = { MyTopAppBar(pokemon) }
    ) {
        if (pokemon != null) {
            PokemonItem(pokemon, it)
        } else {
            Column(
                modifier = Modifier.padding(paddingValues = it),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.pokeballm),
                    contentDescription = "",
                    alignment = Alignment.Center
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(pokemon: Pokemon?) {
    TopAppBar(
        title = { Text(text = "Pokedex", color = Color.White) },
        navigationIcon = {
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = null, tint = Color.White)
            }
        },
        actions = {
            Text(text = "#${pokemon?.pokemonID}", color = Color.White)
        },
        colors = TopAppBarDefaults.largeTopAppBarColors(PokedexColor)
    )
}