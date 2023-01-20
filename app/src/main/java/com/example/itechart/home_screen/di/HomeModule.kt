package com.example.itechart.home_screen.di

import com.example.itechart.home_screen.data.source.remote.HomeServiceApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Provides
    @Singleton
    fun provideHomeApi(retrofit: Retrofit): HomeServiceApi =
        retrofit.create(HomeServiceApi::class.java)
}