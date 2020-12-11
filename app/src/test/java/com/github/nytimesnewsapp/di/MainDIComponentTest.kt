package com.github.nytimesnewsapp.di

import com.github.nytimesnewsapp.base.data.di.serviceModules
import com.github.nytimesnewsapp.base.domain.di.domainModules

fun configureTestAppComponent(baseApi: String) = listOf(
    MockWebServerDIPTest,
    configureNetworkModuleForTest(baseApi),
    serviceModules,
    domainModules
)

