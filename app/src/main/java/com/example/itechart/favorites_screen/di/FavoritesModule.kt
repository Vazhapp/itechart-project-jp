package com.example.itechart.favorites_screen.di

import com.example.itechart.common.Consts.BASE_URL
import com.example.itechart.favorites_screen.data.source.remote.FavoritesServiceApi
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
object FavoritesModule {

    @Provides
    @Singleton
    fun provideFavoritesApi(okHttpClient: OkHttpClient, moshi: Moshi): FavoritesServiceApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(FavoritesServiceApi::class.java)
}