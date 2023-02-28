package com.example.api.response

data class Article(
    val source: Source?,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlImage: String?,
    val publishedAt: String?,
    val content: String?,
)