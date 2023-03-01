package com.example.newsapp.article

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.newsapp.R
import com.example.newsapp.utils.customGreyText
import com.example.newsapp.utils.toTextDp
import com.example.usecase.data.Article

@Composable
fun ArticleDetailsView(
    article: Article,
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        if (article.urlImage != null) {
            AsyncImage(
                model = article.urlImage,
                contentDescription = null,
            )
        }
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)) {
            article.title?.let {
                Text(
                    text = it,
                    style = TextStyle(fontSize = 20.dp.toTextDp())
                )
            }
            article.description?.let { customGreyText(text = it) }
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.End
            ) {
                article.author?.let { customGreyText(text = it) }
                if (article.publishedAt == null) {
                    customGreyText(text = "2023-march-01 09:22")
                } else {
                    article.publishedAt?.let { customGreyText(text = it) }
                }
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                onClick = { onClick() }) {
                Text(text = stringResource(id = R.string.readFullArticleOnlineButtonText))
            }
        }
    }
}