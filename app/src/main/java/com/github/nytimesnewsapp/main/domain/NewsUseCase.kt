package com.github.nytimesnewsapp.main.domain

import com.github.nytimesnewsapp.base.domain.BaseDomainModel
import com.github.nytimesnewsapp.base.domain.BaseUseCase
import com.github.nytimesnewsapp.base.presentation.model.BaseUIModel
import com.github.nytimesnewsapp.main.data.source.NewsRemoteData


class NewsUseCase(
    private val newsRemoteData: NewsRemoteData?
) : BaseUseCase<Params, List<BaseDomainModel>>() {
    override suspend fun run(params: Params): List<BaseDomainModel> {
        return newsRemoteData?.fetchMostViewed(params.period).orEmpty()
    }
}

data class Params(val period: String)
