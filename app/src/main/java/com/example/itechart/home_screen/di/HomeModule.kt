package com.example.itechart.home_screen.di

import com.example.itechart.home_screen.data.repository.HomeRepositoryImpl
import com.example.itechart.home_screen.data.source.remote.HomeServiceApi
import com.example.itechart.home_screen.domain.repository.HomeRepository
import com.example.itechart.home_screen.domain.use_case.GetPodcastListUseCase
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

    @Provides
    @Singleton
    fun provideHomeRepo(homeServiceApi: HomeServiceApi): HomeRepository =
        HomeRepositoryImpl(homeServiceApi)

    @Provides
    @Singleton
    fun provideGetPodcastListUseCase(repository: HomeRepository): GetPodcastListUseCase =
        GetPodcastListUseCase(repository)
}