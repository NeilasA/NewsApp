package com.example.api.response

data class UserResult(
    val status: String?,
    val totalResults: Int?,
    val articles: List<Article>?,
)