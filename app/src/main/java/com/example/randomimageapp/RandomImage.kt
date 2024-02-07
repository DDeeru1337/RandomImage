package com.example.randomimageapp

data class RandomImage(
    val license: String,
    val provider: String,
    val size: Size,
    val terms: String,
    val url: String
)