package com.codingchallenge.ctw.di

import com.codingchallenge.ctw.data.repository.NewsRemoteDataSourceImpl
import com.codingchallenge.ctw.data.service.Service
import com.codingchallenge.ctw.domain.repository.NewsRepository
import com.codingchallenge.ctw.utils.DefaultDispatchProvider
import com.codingchallenge.ctw.utils.DispatchProvider
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("https://newsapi.org/v2/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideOkHttpClient() = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)
        ).addInterceptor(ApiRequestInterceptor())
        .build()

    @Provides
    fun provideService(retrofit: Retrofit): Service = retrofit.create(Service::class.java)

    @Provides
    fun provideRepository(service: Service): NewsRepository = NewsRemoteDataSourceImpl(service)

    @Provides
    fun provideDispatcher(): DispatchProvider = DefaultDispatchProvider()
}