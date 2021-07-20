package com.example.pizzaria.ui.pizzaria

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView.OnItemClickListener
import android.widget.BaseAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pizzaria.R
import com.example.pizzaria.api.ApiInterface
import com.example.pizzaria.api.RetrofitInstance
import com.example.pizzaria.ui.adapter.ItemAdapter
import com.example.pizzaria.utils.Pizzas
import kotlinx.android.synthetic.main.escholha.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class EscolhaActivity : AppCompatActivity() {

    private var pizzas : MutableList<Pizzas> = mutableListOf()
    private lateinit var adapter: BaseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.escholha)

        val retrofitClient = RetrofitInstance
            .getRetrofitInstance()

        val endpoint = retrofitClient.create(ApiInterface::class.java)
        val callback = endpoint.getPizzas()

        callback.enqueue(object : Callback<List<Pizzas>> {
            override fun onFailure(call: Call<List<Pizzas>>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<Pizzas>>, response: Response<List<Pizzas>>) {
                response.body()?.forEach {
                    pizzas.add(it)
                }

                adapter = ItemAdapter(applicationContext, pizzas)
                lista.adapter = adapter
                lista.divider = null
            }
        })


        lista.setOnItemClickListener(OnItemClickListener { _, _, position, _ ->
            escolhePizza(pizzas[position])
        })

    }

    fun escolhePizza(pizza: Pizzas){

        val myIntent = Intent(this, ItemActivity::class.java)
        myIntent.putExtra("nome", pizza.name)
        myIntent.putExtra("priceP", pizza.priceP)
        myIntent.putExtra("priceG", pizza.priceG)
        myIntent.putExtra("priceM", pizza.priceM)
        myIntent.putExtra("image", pizza.image_pizza)
        myIntent.putExtra("rating", pizza.rating_pizza)
        startActivity(myIntent)
    }
}