package com.github.nytimesnewsapp.base.data.di

import com.github.nytimesnewsapp.main.data.source.NewsRemoteData
import org.koin.dsl.module

val serviceModules = module {
    factory {
        NewsRemoteData(get())
    }
}