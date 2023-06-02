package com.example.cryptoapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CryptoCoin(
    val name: String,
    val fullName: String,
    val type: String,
    val imageUrl: String,
    val price: String,
    val lastVolume: String?,
    val lastVolumeTo: String?,
    val lastUpdate: Long
) : Parcelable
