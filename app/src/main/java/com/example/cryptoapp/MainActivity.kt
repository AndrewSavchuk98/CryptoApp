package com.example.cryptoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.cryptoapp.data.CryptoApi
import com.example.cryptoapp.data.CryptoRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var api: CryptoApi

    @Inject
    lateinit var repository: CryptoRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch {
            val result = api.getTopCoinsInfo(limit = 10)
            Log.d("!!!!!!!!!!!", " RESULT $result")
            val priceInfo = api.getFullPriceList(fSyms = "LTC")
            Log.d("!!!!!!!!!!!", "PRICE $priceInfo")

            val data = repository.getCryptoList(50)
            Log.d("MYLIST", "MYLIST is $data" )



        }

    }
}