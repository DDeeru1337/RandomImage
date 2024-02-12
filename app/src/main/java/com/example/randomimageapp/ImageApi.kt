package com.example.randomimageapp

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageApi {
    @GET("/v1/images/search")
    suspend fun getImage(): Response<RandomCatImage>
}