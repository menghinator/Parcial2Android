package com.example.parcial2android

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class Adapter (private val fruits: MutableList<Fruit>, val context: Context) :  RecyclerView.Adapter<Adapter.ViewHolder>() {

    lateinit var onItemClickListener: (Fruit) -> Unit

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val tvFruit: TextView = view.findViewById(R.id.tv_fruit)
        private val ivFruit: ImageView = view.findViewById(R.id.iv_fruit)
        private val defaultImg: String = "https://static.vecteezy.com/system/resources/previews/027/144/592/original/fresh-tasty-mix-fruit-salad-isolated-on-transparent-background-png.png"
        private val persimmonImg: String = "https://static.vecteezy.com/system/resources/previews/022/881/860/original/persimmon-transparent-background-with-generative-ai-png.png"
        private val strawberryImg: String = "https://purepng.com/public/uploads/large/purepng.com-strawberryfruitsberryberriesstrawberrystrawberriesred-981524759547wuott.png"
        private val bananaImg: String = "https://png.pngtree.com/png-clipart/20220716/ourmid/pngtree-banana-yellow-fruit-banana-skewers-png-image_5944324.png"

        fun bind(fruit: Fruit) {
            tvFruit.text = fruit.name
            when (fruit.name){
                "Persimmon" -> fruit.image = persimmonImg
                "Strawberry" -> fruit.image = strawberryImg
                "Banana" -> fruit.image = bananaImg
                else -> fruit.image = defaultImg
            }
            Glide.with(context).load(fruit.image).into(ivFruit)
            view.setOnClickListener {
                onItemClickListener(fruit)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.fruit_item, parent, false))
    }

    override fun getItemCount(): Int {
        return fruits.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fruit = fruits[position]
        holder.bind(fruit)
    }



}




