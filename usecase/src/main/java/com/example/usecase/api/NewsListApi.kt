package com.example.usecase.api

import com.example.usecase.data.Article

interface NewsListApi {
    suspend fun get(): List<Article>
}