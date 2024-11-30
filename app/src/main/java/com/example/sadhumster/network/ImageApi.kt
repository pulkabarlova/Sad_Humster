package com.example.sadhumster.network

import com.example.sadhumster.model.Joke
import com.example.sadhumster.model.JokeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface JokeApi {
    @GET("Any?blacklistFlags=nsfw,religious,political,racist,sexist,explicit&type=twopart&amount=10")
    suspend fun loadJokes()
            : Response<JokeResponse>
}