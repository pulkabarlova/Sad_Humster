package com.example.sadhumster.datasource.network

import com.example.sadhumster.domain.model.JokeResponse
import retrofit2.Response
import retrofit2.http.GET

interface JokeApi {
    @GET("Any?blacklistFlags=nsfw,religious,political,racist,sexist,explicit&type=twopart&amount=10")
    suspend fun loadJokes()
            : Response<JokeResponse>
}