package com.example.newsapp.news

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.newsapp.R
import com.example.newsapp.utils.customGreyText
import com.example.usecase.data.Article

@Composable
fun NewsListItem(
    article: Article,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .clickable { onClick() }
            .clip(shape = RoundedCornerShape(4.dp))
            .background(color = Color.White)
            .padding(vertical = 4.dp, horizontal = 2.dp)
            .padding(16.dp),
    ) {
        if (article.urlImage == null) {
            Image(
                modifier = Modifier.size(84.dp),
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = null
            )
        } else {
            AsyncImage(
                model = article.urlImage,
                contentDescription = null,
            )
        }
        Column(
            modifier = Modifier
                .padding(start = 8.dp)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            article.title?.let { Text(text = it) }
            if (article.publishedAt == null) {
                customGreyText(text = "2023-march-01 09:22")
            } else {
                article.publishedAt?.let { customGreyText(text = it) }
            }
        }
    }
    Spacer(modifier = Modifier.padding(top = 8.dp))
}