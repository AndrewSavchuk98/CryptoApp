package com.example.cryptoapp.data.model

import com.google.gson.annotations.SerializedName

data class CoinInfo(
    @SerializedName("Name")
    val name: String? = null
)
