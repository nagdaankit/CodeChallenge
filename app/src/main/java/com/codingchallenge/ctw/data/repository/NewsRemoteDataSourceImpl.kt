package com.codingchallenge.ctw.data.repository

import com.codingchallenge.ctw.data.mapper.toArticle
import com.codingchallenge.ctw.data.service.Service
import com.codingchallenge.ctw.domain.repository.NewsRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRemoteDataSourceImpl @Inject constructor(
    private val service: Service
) : NewsRepository {

    override fun getHeadlines() = flow {
        emit(service.getTopHeadlines().articleModels?.map { it.toArticle() }
            ?.sortedByDescending { it.publishedAt })
    }

    override fun getEverything() = flow {
        emit(service.getEverything().articleModels?.map { it.toArticle() }
            ?.sortedByDescending { it.publishedAt })
    }
}