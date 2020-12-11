package com.github.nytimesnewsapp

import android.app.Application
import com.github.nytimesnewsapp.base.presentation.di.viewModelModules
import com.github.nytimesnewsapp.base.data.di.networkModule
import com.github.nytimesnewsapp.base.data.di.serviceModules
import com.github.nytimesnewsapp.base.domain.di.domainModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NTimesNewsApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@NTimesNewsApp)
            modules(listOf(networkModule, serviceModules, domainModules, viewModelModules))
        }
    }
}