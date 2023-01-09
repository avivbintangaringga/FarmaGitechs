package com.r7fx.farmagitechs.data.di

import com.google.gson.GsonBuilder
import com.r7fx.farmagitechs.data.api.services.ApiService
import com.r7fx.farmagitechs.data.util.HeaderInterceptor
import com.r7fx.farmagitechs.common.utils.Constants
import com.r7fx.farmagitechs.common.utils.Preferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {
    @Provides
    fun provideGsonConverterFactory() =
        GsonConverterFactory.create(
            GsonBuilder()
                .create()
        )

    @Provides
    fun provideHeaderInterceptor(prefs: Preferences) =
        HeaderInterceptor(prefs)

    @Provides
    fun provideOkHttpClient(headerInterceptor: HeaderInterceptor) =
        OkHttpClient.Builder()
            .addInterceptor(headerInterceptor)
            .connectTimeout(30L, TimeUnit.SECONDS)
            .writeTimeout(30L, TimeUnit.SECONDS)
            .readTimeout(30L, TimeUnit.SECONDS)
            .build()

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, converterFactory: GsonConverterFactory) =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(Constants.BASE_API_URL)
            .addConverterFactory(converterFactory)
            .build()

    @Provides
    fun provideApiService(retrofit: Retrofit) =
        retrofit.create(ApiService::class.java)
}