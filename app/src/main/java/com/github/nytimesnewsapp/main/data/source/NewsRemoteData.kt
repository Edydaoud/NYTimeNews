package com.github.nytimesnewsapp.main.data.source

import com.github.nytimesnewsapp.base.domain.BaseDomainModel
import com.github.nytimesnewsapp.base.domain.ErrorDomainModel
import retrofit2.Retrofit

class NewsRemoteData constructor(
    private val retrofit: Retrofit?
) {
    suspend fun fetchMostViewed(period: String): List<BaseDomainModel> = try {
        retrofit?.create(NewsRemoteService::class.java)
            ?.fetchMostViewed(period)?.results?.map { it.toDomain() }.orEmpty()
    } catch (e: Exception) {
        listOf(ErrorDomainModel(e))
    }
}