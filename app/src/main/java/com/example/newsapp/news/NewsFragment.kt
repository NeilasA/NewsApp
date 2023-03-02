package com.example.newsapp.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.newsapp.databinding.FragmentNewsBinding
import com.example.usecase.data.Article
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : Fragment() {

    private lateinit var binding: FragmentNewsBinding

    private val viewModel by viewModels<NewsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.container.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                val news = viewModel.news.observeAsState()
                val loading = viewModel.loading.collectAsState()
                MaterialTheme {
                    SwipeRefresh(
                        state = rememberSwipeRefreshState(isRefreshing = loading.value),
                        onRefresh = { viewModel.loadNews() }
                    ) {
                        news.value?.let { SetupComposeUi(it) }
                    }
                }
            }
        }
        return view
    }

    @Composable
    private fun SetupComposeUi(news: List<Article>) {
        Column(
            modifier = Modifier
                .background(color = Color.LightGray)
                .padding(top = 4.dp, start = 4.dp, end = 4.dp)
                .fillMaxWidth()
        ) {
            LazyColumn {
                items(news) { article ->
                    println("values news: $news")
                    NewsListItem(
                        article = article,
                        onClick = {
                            val action = NewsFragmentDirections
                                .actionFirstFragmentToSecondFragment(article)
                            findNavController().navigate(action)
                        }
                    )
                }
            }
        }
    }
}