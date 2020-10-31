package com.tuppersoft.marvel.core.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.tuppersoft.data.datasource.MarvelEndPoints
import com.tuppersoft.data.datasource.MarvelServices
import com.tuppersoft.marvel.BuildConfig
import com.tuppersoft.marvel.core.extension.md5
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.OkHttpClient.Builder
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit.SECONDS
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun createNetworkClient() = retrofitClient(httpClient())

    @Provides
    @Singleton
    fun provideServices(): MarvelServices = createNetworkClient().create(MarvelServices::class.java)

    private fun httpClient(): OkHttpClient {

        return Builder().apply {

            addInterceptor(
                HttpLoggingInterceptor().setLevel(
                    getLogLevel()
                )
            )

            addInterceptor { chain ->
                val currentTimestamp = System.currentTimeMillis()
                val newUrl = chain.request().url
                    .newBuilder()
                    .addQueryParameter("ts", currentTimestamp.toString())
                    .addQueryParameter("apikey", MarvelEndPoints.PUBLIC_API_KEY)
                    .addQueryParameter(
                        "hash",
                        (currentTimestamp.toString() + MarvelEndPoints.PRIVATE_API_KEY + MarvelEndPoints.PUBLIC_API_KEY).md5()
                    )
                    .build()

                val newRequest = chain.request()
                    .newBuilder()
                    .url(newUrl)
                    .build()
                chain.proceed(newRequest)
            }

            addNetworkInterceptor(StethoInterceptor())
            readTimeout(30, SECONDS)
            writeTimeout(30, SECONDS)
        }.build()
    }

    private fun retrofitClient(httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(MarvelEndPoints.BASE_URL)
            .client(httpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    private fun getLogLevel(): HttpLoggingInterceptor.Level {
        return if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.HEADERS
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }
}




