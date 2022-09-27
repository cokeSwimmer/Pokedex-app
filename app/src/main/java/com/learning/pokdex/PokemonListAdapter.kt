package com.learning.pokdex

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.json.JSONArray


class PokemonListAdapter():RecyclerView.Adapter<PokemonViewHolder>() {

    private val items:ArrayList<Pokemon> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.each_item, parent, false)
        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val currentItem = items[position]
        holder.name.text =currentItem.name
        holder.num.text ="#"+currentItem.num
        Glide.with(holder.itemView.context).load(currentItem.img).into(holder.img)

        holder.eachItemLayout.setOnClickListener{
            val intent = Intent(holder.itemView.context,Second::class.java)

            val bundle =Bundle()
            bundle.putString("name",currentItem.name)
            bundle.putString("img",currentItem.img)
            val typeString:String =JSONArrayToString(currentItem.type)
            bundle.putString("type",typeString)
            bundle.putString("height",currentItem.height)
            bundle.putString("weight",currentItem.weight)
            val weaknessesString:String=JSONArrayToString(currentItem.weaknesses)
            bundle.putString("weaknesses",weaknessesString)
            intent.putExtra("bundle",bundle)
            startActivity(it.context, intent,null)
        }
    }

    private fun JSONArrayToString(JsArray: JSONArray): String {
        val list = arrayListOf<String>()
        for (i in 0 until JsArray.length()){list.add(JsArray.getString(i))}
        var typeString = ""
        for (element in list){ typeString+= " "+element}
        return typeString
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateItems(updateItems: ArrayList<Pokemon>){
        items.clear()
        items.addAll(updateItems)
        notifyDataSetChanged()
    }

}
class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val name: TextView = itemView.findViewById(R.id.name)
    val num: TextView = itemView.findViewById(R.id.num)
    val img: ImageView = itemView.findViewById(R.id.img)
    val eachItemLayout:ConstraintLayout = itemView.findViewById(R.id.each_item_layout)
}