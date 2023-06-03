package com.example.cryptocoinapp.di

import com.example.cryptocoinapp.data.CryptoApi
import com.example.cryptocoinapp.data.CryptoRepository
import com.example.cryptocoinapp.data.CryptoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideCoinRepository(service: CryptoApi): CryptoRepository{
        return CryptoRepositoryImpl(service)
    }
}