package com.example.usecase.news

import com.example.usecase.api.NewsListApi
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val api: NewsListApi
) {
    suspend fun invoke() = api.get()
}