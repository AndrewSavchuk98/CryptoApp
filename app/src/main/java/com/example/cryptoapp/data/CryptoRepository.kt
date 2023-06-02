package com.example.cryptoapp.data

interface CryptoRepository {

    suspend fun getCrypto()
}