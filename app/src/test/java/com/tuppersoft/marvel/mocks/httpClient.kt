package com.tuppersoft.marvel.mocks

import com.tuppersoft.data.datasource.MarvelEndPoints
import com.tuppersoft.marvel.core.extension.md5
import okhttp3.OkHttpClient
import okhttp3.OkHttpClient.Builder
import java.util.concurrent.TimeUnit.SECONDS

fun getMockHttpClient(publicApiKey: String = MarvelEndPoints.PUBLIC_API_KEY): OkHttpClient {

    return Builder().apply {
        addInterceptor { chain ->
            val currentTimestamp = System.currentTimeMillis()
            val newUrl = chain.request().url
                .newBuilder()
                .addQueryParameter("ts", currentTimestamp.toString())
                .addQueryParameter("apikey", MarvelEndPoints.PUBLIC_API_KEY)
                .addQueryParameter(
                    "hash",
                    (currentTimestamp.toString() + MarvelEndPoints.PRIVATE_API_KEY + publicApiKey).md5()
                )
                .build()

            val newRequest = chain.request()
                .newBuilder()
                .url(newUrl)
                .build()
            chain.proceed(newRequest)
        }

        readTimeout(30, SECONDS)
        writeTimeout(30, SECONDS)
    }.build()
}

fun getMockhttpClientWithBadToken(): OkHttpClient {
    return getMockHttpClient("token_falso")
}
