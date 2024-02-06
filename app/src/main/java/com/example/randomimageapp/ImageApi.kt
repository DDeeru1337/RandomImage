package com.example.randomimageapp

import RandomImage
import retrofit2.Response
import retrofit2.http.GET

interface ImageApi {
    @GET("/v1/image")
    suspend fun getImage(): Response<RandomImage>
}