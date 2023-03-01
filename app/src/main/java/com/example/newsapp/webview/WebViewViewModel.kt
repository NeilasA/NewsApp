package com.example.newsapp.webview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WebViewViewModel @Inject constructor(
    state: SavedStateHandle
) : ViewModel() {

    private val _articleUrl = MutableLiveData("")
    val articleUrl: LiveData<String> = _articleUrl

    init {
        _articleUrl.value = state.get<String>("NewsUrl")
    }
}