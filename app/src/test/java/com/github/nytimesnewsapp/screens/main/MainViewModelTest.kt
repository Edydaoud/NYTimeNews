package com.github.nytimesnewsapp.screens.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.nytimesnewsapp.base.BaseUTTest
import com.github.nytimesnewsapp.di.configureTestAppComponent
import com.github.nytimesnewsapp.main.data.model.MostPopularResponse
import com.github.nytimesnewsapp.main.domain.NewsUseCase
import com.github.nytimesnewsapp.main.presentation.viewmodel.MainViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin

@RunWith(JUnit4::class)
class MainViewModelTest : BaseUTTest() {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var mMainViewModel: MainViewModel

    @MockK
    lateinit var mNewsUseCase: NewsUseCase

    @Before
    fun start() {
        super.setUp()
        //Used for initiation of Mockk
        MockKAnnotations.init(this)
        //Start Koin with required dependencies
        startKoin { modules(configureTestAppComponent(getMockWebServerUrl())) }
    }

    @Test
    fun test_login_view_model_data_populates_expected_value() {

        mMainViewModel = MainViewModel(mNewsUseCase)

        val sampleResponse = getJson("test.json")
        val jsonObj = Json { ignoreUnknownKeys = true }.decodeFromString<MostPopularResponse>(
            MostPopularResponse.serializer(),
            sampleResponse
        )

        coEvery { mNewsUseCase.run(any()) } returns jsonObj.results.map { it.toDomain() }

        mMainViewModel.setBaseState {
            copy(list = jsonObj.results.map { it.toDomain() }
                .mapIndexed { index, newsDomainModel -> newsDomainModel.toUIModel(index) })
        }

        mMainViewModel.stateLiveData.observeForever { }

        assert(mMainViewModel.stateLiveData.value != null)

        val testResult =
            mMainViewModel.stateLiveData.value?.list
        assert(testResult?.isNotEmpty() == true)
    }
}