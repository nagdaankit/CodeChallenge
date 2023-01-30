package com.codingchallenge.ctw

import app.cash.turbine.test
import com.codingchallenge.ctw.domain.models.Article
import com.codingchallenge.ctw.domain.repository.NewsRepository
import com.codingchallenge.ctw.ui.MainViewModel
import com.codingchallenge.ctw.utils.DispatchProvider
import com.codingchallenge.ctw.utils.Resource
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @Mock
    private lateinit var repository: NewsRepository
    private lateinit var dispatcher: DispatchProvider

    @Before
    fun setUp() {
        dispatcher = TestDispatcherProvider()
    }

    @Test
    fun should_return_success_when_top_headlines_is_fetched_and_response_is_200() {
        runTest {
            doReturn(flowOf(emptyList<Article>())).`when`(repository).getHeadlines()
            val viewModel = MainViewModel(repository, dispatcher)
            viewModel.news.test {
                assertEquals(Resource.success(emptyList<List<Article>>()), awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
            verify(repository).getHeadlines()
        }
    }

    @Test
    fun should_return_success_when_everything_is_fetched_and_response_is_200() {
        runTest {
            doReturn(flowOf(emptyList<Article>())).`when`(repository).getEverything()
            val viewModel = MainViewModel(repository, dispatcher)
            viewModel.news.test {
                assertEquals(Resource.success(emptyList<List<Article>>()), awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
            verify(repository).getEverything()
        }
    }

    @Test
    fun should_return_error_when_the_top_headlines_response_is_not_200() {
        runTest {
            val error = "Some error occurred"
            doReturn(flow<List<Article>> {
                throw IllegalStateException(error)
            }).`when`(repository).getHeadlines()

            val viewModel = MainViewModel(repository, dispatcher)
            viewModel.news.test {
                assertEquals(
                    Resource.error<List<Article>>(IllegalStateException(error).toString()),
                    awaitItem()
                )
                cancelAndIgnoreRemainingEvents()
            }
            verify(repository).getHeadlines()
        }
    }

    @Test
    fun should_return_error_when_everything_response_is_not_200() {
        runTest {
            val error = "Some error occurred"
            doReturn(flow<List<Article>> {
                throw IllegalStateException(error)
            }).`when`(repository).getEverything()

            val viewModel = MainViewModel(repository, dispatcher)
            viewModel.news.test {
                assertEquals(
                    Resource.error<List<Article>>(IllegalStateException(error).toString()),
                    awaitItem()
                )
                cancelAndIgnoreRemainingEvents()
            }
            verify(repository).getEverything()
        }
    }
}