package com.codingchallenge.ctw.domain.repository

import com.codingchallenge.ctw.domain.models.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getHeadlines(): Flow<List<Article>?>
    fun getEverything(): Flow<List<Article>?>
}