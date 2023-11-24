package com.example.parcial2android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class FruitActivity : AppCompatActivity() {

    private lateinit var  ivFruit: ImageView
    private lateinit var tvName: TextView
    private lateinit var tvFamily: TextView
    private lateinit var tvGenus: TextView
    private lateinit var tvOrder: TextView
    private lateinit var tvCalories: TextView
    private lateinit var tvCarbs: TextView
    private lateinit var tvFat: TextView
    private lateinit var tvSugar: TextView
    private lateinit var tvProtein: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fruit)

        val fruit = intent.getParcelableExtra<Fruit>("fruit")
        val nutrition = intent.getParcelableExtra<Nutrition>("nutrition")


        tvName = findViewById(R.id.tv_fruitName)
        tvFamily = findViewById(R.id.tv_family)
        tvGenus = findViewById(R.id.tv_genus)
        tvOrder = findViewById(R.id.tv_order)
        tvCalories = findViewById(R.id.tv_cal)
        tvCarbs = findViewById(R.id.tv_carbs)
        tvFat = findViewById(R.id.tv_fat)
        tvSugar = findViewById(R.id.tv_sugar)
        tvProtein = findViewById(R.id.tv_protein)
        ivFruit = findViewById(R.id.ivFruit)


        tvName.text = fruit?.name
        tvFamily.text = fruit?.family
        tvGenus.text = fruit?.genus
        tvOrder.text = fruit?.order
        tvCalories.text = nutrition?.calories.toString()
        tvCarbs.text = nutrition?.carbohydrates.toString()
        tvFat.text = nutrition?.fat.toString()
        tvSugar.text = nutrition?.sugar.toString()
        tvProtein.text = nutrition?.protein.toString()
        Glide.with(this).load(fruit?.image).into(ivFruit)
    }
}