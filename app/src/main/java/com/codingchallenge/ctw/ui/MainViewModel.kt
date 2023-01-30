package com.codingchallenge.ctw.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingchallenge.ctw.BuildConfig
import com.codingchallenge.ctw.domain.models.Article
import com.codingchallenge.ctw.domain.repository.NewsRepository
import com.codingchallenge.ctw.utils.DispatchProvider
import com.codingchallenge.ctw.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: NewsRepository,
    private val dispatchProvider: DispatchProvider
) : ViewModel() {

    private val _news = MutableStateFlow<Resource<List<Article>>>(Resource.loading())

    val news: StateFlow<Resource<List<Article>>> = _news

    init {
        fetchNews()
    }

    private fun fetchNews() {
        viewModelScope.launch(dispatchProvider.main) {
            _news.value = Resource.loading()
            getEndpoint()
                .flowOn(dispatchProvider.io)
                .catch { e ->
                    _news.value = Resource.error(e.toString())
                }
                .collect {
                    _news.value = Resource.success(it)
                }
        }
    }

    private fun getEndpoint() = if (BuildConfig.FLAVOR.equals(
            "everything",
            true
        )
    ) repository.getEverything() else repository.getHeadlines()
}