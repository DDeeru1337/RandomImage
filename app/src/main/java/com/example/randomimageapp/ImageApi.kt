package com.example.randomimageapp

import retrofit2.Response
import retrofit2.http.GET

interface ImageApi {
    @GET("/v1/image?width=500&height=150&category=buildings&format=json")
    suspend fun getImage(): Response<RandomImage>
}