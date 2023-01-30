package com.codingchallenge.ctw.data.mapper

import com.codingchallenge.ctw.data.entities.ArticleModel
import com.codingchallenge.ctw.data.entities.SourceModel
import com.codingchallenge.ctw.domain.models.Article
import com.codingchallenge.ctw.domain.models.Source

fun ArticleModel.toArticle(): Article {
    return Article(
        sourceModel = sourceModel?.toSource(),
        author = author,
        title = title,
        description = description,
        url = url,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        content = content
    )
}

fun SourceModel.toSource(): Source {
    return Source(id = id, name = name)
}