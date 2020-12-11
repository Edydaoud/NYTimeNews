package com.github.nytimesnewsapp.base.data.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit

private const val API_URL = "https://api.nytimes.com/svc/mostpopular/v2/"
private const val API_KEY = "9mBuOoTz4qvS7PGYKGARTKmGmID4CnuJ"

val networkModule = module {
    factory { AuthInterceptor() }
    factory { provideOkHttpClient(get()) }
    single { provideRetrofit(get()) }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    val contentType = "application/json".toMediaType()

    return Retrofit.Builder().baseUrl(API_URL)
        .addConverterFactory(Json {
            ignoreUnknownKeys = true
        }.asConverterFactory(contentType))
        .client(okHttpClient).build()
}

fun provideOkHttpClient(
    authInterceptor: AuthInterceptor
): OkHttpClient {
    return OkHttpClient().newBuilder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        })
        .addInterceptor(authInterceptor).build()
}

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var req = chain.request()
        val url = req.url.newBuilder().addQueryParameter("api-key", API_KEY).build()
        req = req.newBuilder().url(url).build()
        return chain.proceed(req)
    }
}