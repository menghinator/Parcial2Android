package com.example.parcial2android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var rvFruits: RecyclerView
    private lateinit var adapter: Adapter
    private var listFruits = mutableListOf<Fruit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvFruits = findViewById(R.id.rv_fruits)
        rvFruits.layoutManager = GridLayoutManager(this,2)
        println(listFruits)
        adapter = Adapter(listFruits,this)
        rvFruits.adapter = adapter

        adapter.onItemClickListener = { fruit ->
            var nutrition = getNutrients(fruit)
            val intent = Intent(this, FruitActivity::class.java)
            intent.putExtra("fruit", fruit)
            intent.putExtra("nutrition", nutrition)
            startActivity(intent)
        }
        getFruits()

    }

    private fun getFruits(){
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(ApiService::class.java).getAllFruits(ALL_FRUITS)
            val response = call.body()

            runOnUiThread{
                if (call.isSuccessful){
                    if (response != null) {
                        response.map { listFruits.add(it) }
                        println(listFruits)
                    }
                }
                adapter.notifyDataSetChanged()
            }
        }
    }
    private fun getNutrients(fruit: Fruit) : Nutrition{
        val nutrition = fruit.nutritions
        return nutrition
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    companion object {
        const val BASE_URL = "https://fruityvice.com/api/"
        const val ALL_FRUITS = "fruit/all"
    }
}