package com.example.usecase.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article(
    val source: Source?,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlImage: String?,
    val publishedAt: String?,
    val content: String?,
) : Parcelable