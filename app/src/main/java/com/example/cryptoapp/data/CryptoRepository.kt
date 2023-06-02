package com.example.cryptoapp.data

import com.example.cryptoapp.domain.model.CryptoCoin

interface CryptoRepository {

    suspend fun getCryptoList(limits: Int): List<CryptoCoin>
}