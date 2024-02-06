package com.example.randomimageapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api: ImageApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://random.imagecdn.app")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ImageApi::class.java)
    }
}