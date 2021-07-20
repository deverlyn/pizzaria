package com.example.pizzaria.ui.pizzaria

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pizzaria.R
import kotlinx.android.synthetic.main.item_escolhido.*


class ItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.escholha)

        val myIntent = intent

        val name = myIntent.getStringExtra("nome")
        val priceP = myIntent.getStringExtra("priceP")
        val priceM = myIntent.getStringExtra("priceM")
        val priceG = myIntent.getStringExtra("priceG")
        val img = myIntent.getStringExtra("image")
        val rating = myIntent.getStringExtra("rating")

        nome.text = name
        rating_stars.numStars = rating!!.toInt()
        valor.text = "RS: "+priceP+",00"

        p.setOnClickListener {
            valor.text = "RS: "+priceP+",00"
        }

        m.setOnClickListener{
            valor.text = "RS: "+priceM+",00"
        }

        g.setOnClickListener {
            valor.text = "RS: "+priceG+",00"
        }

        comprar.setOnClickListener {
            setContentView(R.layout.success)
        }

    }
}