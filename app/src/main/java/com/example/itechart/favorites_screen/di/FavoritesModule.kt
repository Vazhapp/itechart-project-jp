package com.example.itechart.favorites_screen.di

import com.example.itechart.favorites_screen.data.source.remote.FavoritesServiceApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FavoritesModule {

    @Provides
    @Singleton
    fun provideFavoritesApi(retrofit: Retrofit): FavoritesServiceApi =
        retrofit.create(FavoritesServiceApi::class.java)
}