package com.codingchallenge.ctw.data.entities

import com.google.gson.annotations.SerializedName
import java.util.*

data class ArticleModel(
    @SerializedName("source")
    val sourceModel: SourceModel?,
    @SerializedName("author")
    val author: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("urlToImage")
    val urlToImage: String?,
    @SerializedName("publishedAt")
    val publishedAt: Date?,
    @SerializedName("content")
    val content: String?
)