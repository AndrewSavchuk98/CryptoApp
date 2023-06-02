package com.example.cryptoapp.di

import com.example.cryptoapp.data.CryptoApi
import com.example.cryptoapp.data.CryptoRepository
import com.example.cryptoapp.data.CryptoRepositoryImpl
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