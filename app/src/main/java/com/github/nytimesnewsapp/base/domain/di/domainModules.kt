package com.github.nytimesnewsapp.base.domain.di

import com.github.nytimesnewsapp.main.domain.NewsUseCase
import org.koin.dsl.module

val domainModules = module {
    factory {
        NewsUseCase(get())
    }
}