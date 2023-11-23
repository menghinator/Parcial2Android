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
        private val urlImage: String = "https://png.pngtree.com/element_our/20190528/ourmid/pngtree-a-strawberry-image_1144087.jpg"

        fun bind(fruit: Fruit) {
            tvFruit.text = fruit.name
            Glide.with(context).load(urlImage).into(ivFruit)

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




