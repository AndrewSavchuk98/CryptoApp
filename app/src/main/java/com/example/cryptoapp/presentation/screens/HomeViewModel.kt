package com.example.cryptoapp.presentation.screens

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptoapp.data.CryptoRepository
import com.example.cryptoapp.domain.model.CryptoCoin
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: CryptoRepository) : ViewModel() {

    val coinsLiveData: MutableLiveData<List<CryptoCoin>> = MutableLiveData()

    init{
        getCoins()
    }

    private fun getCoins() {
        try {
            viewModelScope.launch {
                coinsLiveData.value = repository.getCryptoList(limits = 50)
            }
        } catch (e: Exception){
            e.printStackTrace()
        }

    }
}