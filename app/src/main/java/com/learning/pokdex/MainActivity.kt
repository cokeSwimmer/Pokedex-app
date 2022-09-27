package com.learning.pokdex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var pAdapter:PokemonListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager= LinearLayoutManager(this)
        fetchData()
        pAdapter= PokemonListAdapter()
        recyclerView.adapter = pAdapter

    }
    private fun fetchData(){
        val url = "https://raw.githubusercontent.com/Biuni/PokemonGO-Pokedex/master/pokedex.json"
        val jsonObjectRequest=JsonObjectRequest(
            Request.Method.GET,url,null,
            {
                val pokemonJSONArray = it.getJSONArray("pokemon")
                val pokemonArray = ArrayList<Pokemon>()
                for (i in 0 until pokemonJSONArray.length()){
                    val pokemonJSONObject = pokemonJSONArray.getJSONObject(i)
                    val pokemon = Pokemon(
                        pokemonJSONObject.getString("num"),
                        pokemonJSONObject.getString("name"),
                        pokemonJSONObject.getString("img"),
                        pokemonJSONObject.getJSONArray("type"),
                        pokemonJSONObject.getString("height"),
                        pokemonJSONObject.getString("weight"),
                        pokemonJSONObject.getJSONArray("weaknesses")
                    )
                    pokemonArray.add(pokemon)
                }
                pAdapter.updateItems(pokemonArray)
            },
            {
                Toast.makeText(this,"Check your connection", Toast.LENGTH_SHORT).show()
            }
        )
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }
}