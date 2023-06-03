package com.example.cryptocoinapp.data

import com.example.cryptocoinapp.domain.model.CryptoCoin

interface CryptoRepository {

    suspend fun getCryptoList(limits: Int): List<CryptoCoin>
}