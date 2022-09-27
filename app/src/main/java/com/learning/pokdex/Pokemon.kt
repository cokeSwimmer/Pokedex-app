package com.learning.pokdex

import org.json.JSONArray

data class Pokemon(
    val num:String,
    val name:String,
    val img:String,
    val type: JSONArray,
    val height: String,
    val weight:String,
    val weaknesses: JSONArray
)
