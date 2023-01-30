package com.codingchallenge.ctw.data.entities

import com.google.gson.annotations.SerializedName

data class NewsBaseResponse(
    @SerializedName("status")
    val status: String?,
    @SerializedName("totalResults")
    val totalResults: String?,
    @SerializedName("articles")
    val articleModels: List<ArticleModel>?
)
