package com.example.api.services

import com.example.api.api.NewsApi
import com.example.usecase.api.NewsListApi
import com.example.usecase.data.Article
import com.example.usecase.data.Source
import javax.inject.Inject

class ApiService @Inject constructor(
    private val api: NewsApi
) : NewsListApi {
    override suspend fun get(): List<Article> {
        val apiValue =  api.getNews().body()?.articles?.map { article ->
            Article(
                source = Source(article.source?.id, article.source?.name),
                author = article.author,
                title = article.title,
                description = article.description,
                url = article.url,
                urlImage = article.urlImage,
                publishedAt = article.publishedAt,
                content = article.content,
            )
        } ?: emptyList()
        return apiValue
    }
}