package com.example.pizzaria.api

import com.example.pizzaria.utils.Pizzas
import com.example.pizzaria.utils.SignInBody
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


interface ApiInterface{
    @Headers("Content-Type:application/json")
    @POST("signin")
    fun login(@Body info :SignInBody):Call<ResponseBody>

    @GET("pizza")
    fun getPizzas() : Call<List<Pizzas>>
}

class RetrofitInstance{
    companion object{
        val BASE_URL : String = "https://p3teufi0k9.execute-api.us-east-1.amazonaws.com/v1/"

        val interceptor : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        val client: OkHttpClient = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
        }.build()

        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}