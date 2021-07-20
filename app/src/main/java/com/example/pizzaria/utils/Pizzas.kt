package com.example.pizzaria.utils

import com.google.gson.annotations.SerializedName

data class Pizzas(
    @SerializedName("id")
    var id : String,
    @SerializedName("name")
    var name : String,
    @SerializedName("imageUrl")
    var image_pizza : String,
    @SerializedName("rating")
    var rating_pizza : Int,
    @SerializedName("priceP")
    var priceP : Double,
    @SerializedName("priceM")
    var priceM : Double,
    @SerializedName("priceG")
    var priceG : Double
)