package com.example.cryptoapp.data

import android.util.Log
import com.example.cryptoapp.data.model.CoinPriceInfo
import com.example.cryptoapp.data.model.CoinPriceInfoRawData
import com.example.cryptoapp.domain.model.CryptoCoin
import com.google.gson.Gson
import javax.inject.Inject

class CryptoRepositoryImpl @Inject constructor(
    private val service: CryptoApi
) : CryptoRepository {

    override suspend fun getCryptoList(limits: Int): List<CryptoCoin> {
        val topCoinsInfo = service.getTopCoinsInfo(limit = 50)
            .data?.mapNotNull { it.coinInfo?.name }?.joinToString(",")

        val fullPriceList = topCoinsInfo?.let {
            service.getFullPriceList(fSyms = it)
        }

        val priceList = fullPriceList?.let {
            getPriceListFromRawData(it)
        }

        val list = mutableListOf<String>()
        service.getTopCoinsInfo(limit = 50).data?.map {
            it.coinInfo?.name?.let { name -> list.add(name) }
        }
        priceList?.map {
            it.price
            it.imageUrl
        }
        return priceList!!.map { mapToCryptoCoin(it) }
    }

    private fun getPriceListFromRawData(
        coinPriceInfoRawData: CoinPriceInfoRawData
    ): List<CoinPriceInfo> {
        val result = ArrayList<CoinPriceInfo>()
        val jsonObject = coinPriceInfoRawData.coinPriceInfoJsonObject ?: return result
        val coinKeySet = jsonObject.keySet()
        for (coinKey in coinKeySet) {
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()
            for (currencyKey in currencyKeySet) {
                val priceInfo = Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey),
                    CoinPriceInfo::class.java
                )
                result.add(priceInfo)
            }
        }
        return result
    }

    private fun mapToCryptoCoin(coinPriceInfo: CoinPriceInfo): CryptoCoin {
        return CryptoCoin(
            name = coinPriceInfo.fromSymbol,
            fullName = coinPriceInfo.fromSymbol,
            type = coinPriceInfo.type ?: "",
            imageUrl = coinPriceInfo.getFullImageUrl(),
            price = coinPriceInfo.price ?: "",
            lastVolume = coinPriceInfo.lastVolume,
            lastVolumeTo = coinPriceInfo.lastVolumeTo,
            lastUpdate = coinPriceInfo.lastUpdate ?: 0
        )
    }

}