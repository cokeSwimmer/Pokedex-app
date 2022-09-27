package com.learning.pokdex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_second.*

class Second : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val bundle = intent.getBundleExtra("bundle")
        nameSec.text=bundle?.getString("name")
        heightData.text=bundle?.getString("height")
        weightData.text=bundle?.getString("weight")
        typeData.text=bundle?.getString("type")
        weaknessesData.text=bundle?.getString("weaknesses")
        Glide.with(this).load(bundle?.getString("img")).into(imgSec)
    }
}