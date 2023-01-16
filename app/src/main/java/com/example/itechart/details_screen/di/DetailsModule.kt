package com.example.itechart.details_screen.di

import com.example.itechart.common.Consts.BASE_URL
import com.example.itechart.details_screen.data.source.remote.DetailsServiceApi
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DetailsModule {

    @Provides
    @Singleton
    fun provideDetailsApi(okHttpClient: OkHttpClient, moshi: Moshi): DetailsServiceApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(DetailsServiceApi::class.java)
}