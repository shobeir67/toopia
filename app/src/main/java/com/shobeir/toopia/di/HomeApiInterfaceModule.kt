package com.shobeir.toopia.di

import com.shobeir.toopia.data.remote.HomeApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object HomeApiInterfaceModule {

    @Singleton
    @Provides
    fun provideHomeApiService(retrofit: Retrofit) : HomeApiInterface =
        retrofit.create(HomeApiInterface::class.java)
}