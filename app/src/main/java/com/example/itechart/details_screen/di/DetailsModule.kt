package com.example.itechart.details_screen.di

import com.example.itechart.details_screen.data.source.remote.DetailsServiceApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DetailsModule {

    @Provides
    @Singleton
    fun provideDetailsApi(retrofit: Retrofit): DetailsServiceApi =
        retrofit.create(DetailsServiceApi::class.java)
}