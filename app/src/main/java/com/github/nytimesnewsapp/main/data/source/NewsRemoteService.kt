package com.github.nytimesnewsapp.main.data.source

import com.github.nytimesnewsapp.main.data.model.MostPopularResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface NewsRemoteService {

    @GET("viewed/{period}.json")
    suspend fun fetchMostViewed(@Path("period") period: String): MostPopularResponse

}
