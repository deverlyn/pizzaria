package com.example.pizzaria.ui.adapter

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.pizzaria.R
import com.example.pizzaria.utils.Pizzas
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_escolha.view.*
import kotlinx.android.synthetic.main.login.view.*
import kotlin.String as String1

class ItemAdapter(private val context:Context, private val pizzas : List<Pizzas>) : BaseAdapter() {

    override fun getCount(): Int {
        return pizzas.size
    }

    override fun getItem(position: Int): Pizzas {
        return pizzas[position]
    }

    override fun getItemId(position: Int): Long {
        return 1
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val viewCriada = LayoutInflater.from(context).inflate(R.layout.item_escolha, parent, false)
        val itemPizza = pizzas[position]

        //Picasso.with(context).load(itemPizza.image_pizza).into(viewCriada.imageView)
        viewCriada.nome.text = itemPizza.name
        viewCriada.valor.text = "RS: "+itemPizza.priceP.toString()+".00"
        viewCriada.rating.numStars = itemPizza.rating_pizza


        return viewCriada
    }
}