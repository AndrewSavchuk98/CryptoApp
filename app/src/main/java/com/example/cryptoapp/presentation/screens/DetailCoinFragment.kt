package com.example.cryptoapp.presentation.screens

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import coil.load
import com.example.cryptoapp.R
import com.example.cryptoapp.databinding.FragmentDetailCoinBinding
import com.example.cryptoapp.domain.model.CryptoCoin
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random

@AndroidEntryPoint
class DetailCoinFragment : Fragment(R.layout.fragment_detail_coin) {

    private lateinit var binding: FragmentDetailCoinBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailCoinBinding.bind(view)

        val coin = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requireArguments().getParcelable(COIN_EXTRA, CryptoCoin::class.java)
        } else {
            requireArguments().getParcelable(COIN_EXTRA)
        }
        with(binding) {
            coin?.let {
                iconImageView.load(it.imageUrl)
                nameTextView.text = "${it.name} / ${it.fullName}"
                volumeTextView.text = it.lastVolumeTo
                val color = if (Random.nextBoolean()) {
                    resources.getColor(R.color.green)
                } else {
                    resources.getColor(R.color.red)
                }
                volumeTextView.setTextColor(color)
                priceTextView.text = "$ ${it.price}"
                sumInWalletTextView.text = "0.00 ${it.name}"
            }

        }

    }

    companion object {
        private const val COIN_EXTRA = "com.example.cryptoapp.presentation.screens.COIN_EXTRA"

        fun putArgs(coin: CryptoCoin): Bundle {
            return bundleOf(COIN_EXTRA to coin)
        }
    }
}