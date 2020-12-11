package com.github.nytimesnewsapp.base.presentation.di

import com.github.nytimesnewsapp.details.presentation.viewmodel.DetailsViewModel
import com.github.nytimesnewsapp.main.presentation.viewmodel.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModules = module {

    viewModel { MainViewModel(get()) }
    viewModel { DetailsViewModel() }

}