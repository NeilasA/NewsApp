package com.example.newsapp.article

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.newsapp.databinding.FragmentNewsArticleBinding
import com.example.usecase.data.Article
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsArticleFragment : Fragment() {

    private lateinit var binding: FragmentNewsArticleBinding

    private val viewModel by viewModels<NewsArticleViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsArticleBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.container.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                val article = viewModel.article.observeAsState()
                MaterialTheme {
                    article.value?.let { SetupComposeUi(it) }
                }
            }
        }
        return view
    }

    @Composable
    private fun SetupComposeUi(article: Article) {
        Column(
            modifier = Modifier
                .background(color = Color.LightGray)
                .padding(top = 4.dp, start = 4.dp, end = 4.dp)
                .fillMaxWidth()
        ) {
            ArticleDetailsView(
                article = article,
                onClick = {
                    article.url?.let {
                        val action = NewsArticleFragmentDirections
                            .actionNewsArticleFragmentToWebViewFragment(it)
                        findNavController().navigate(action)
                    }
                }
            )
        }
    }
}