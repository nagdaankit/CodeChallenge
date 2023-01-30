package com.codingchallenge.ctw.data.service

import com.codingchallenge.ctw.data.entities.NewsBaseResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {
    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String = "US",
    ): NewsBaseResponse

    @GET("everything")
    suspend fun getEverything(
        @Query("q") query: String = "Android",
    ): NewsBaseResponse
}