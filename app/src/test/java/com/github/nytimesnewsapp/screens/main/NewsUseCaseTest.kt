package com.github.nytimesnewsapp.screens.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.nytimesnewsapp.base.BaseUTTest
import com.github.nytimesnewsapp.di.configureTestAppComponent
import com.github.nytimesnewsapp.main.data.source.NewsRemoteData
import com.github.nytimesnewsapp.main.domain.NewsUseCase
import com.github.nytimesnewsapp.main.domain.Params
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin
import org.koin.test.get
import java.net.HttpURLConnection

@RunWith(JUnit4::class)
class NewsUseCaseTest : BaseUTTest() {

    //Target
    private lateinit var mNewsUseCase: NewsUseCase

    private lateinit var newsRemoteData: NewsRemoteData

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    val mParam = Params("1")

    @Before
    fun start() {
        super.setUp()
        //Start Koin with required dependencies
        startKoin { modules(configureTestAppComponent(getMockWebServerUrl())) }
    }

    @Test
    fun test_login_use_case_returns_expected_value() = runBlocking {

        newsRemoteData = get()
        mockNetworkResponseWithFileContent("test.json", HttpURLConnection.HTTP_OK)
        mNewsUseCase = NewsUseCase(newsRemoteData)

        val dataReceived = mNewsUseCase.run(mParam)

        assertNotNull(dataReceived)
        assert(dataReceived.isNotEmpty())
    }

}