package com.example.newsapp.article

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.usecase.data.Article
import com.example.usecase.data.Source
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsArticleViewModel @Inject constructor(
    state: SavedStateHandle
) : ViewModel() {

    private val _article = MutableLiveData(EMPTY_ARTICLE)
    val article: LiveData<Article> = _article

    init {
        _article.value = state.get<Article>("Article")
    }

    companion object {
        private val EMPTY_ARTICLE = Article(
            source = Source("",""),
            author = "",
            title = "",
            description = "",
            url = "",
            urlImage = "",
            publishedAt = "",
            content = "",
        )
    }
}