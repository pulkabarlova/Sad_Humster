package com.example.sadhumster.network
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    val URL = "https://v2.jokeapi.dev/joke/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val api: JokeApi by lazy {
        retrofit.create(JokeApi::class.java)
    }
}