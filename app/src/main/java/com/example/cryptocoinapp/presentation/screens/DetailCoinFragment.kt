package com.example.cryptocoinapp.presentation.screens

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import coil.load
import com.example.cryptocoinapp.R
import com.example.cryptocoinapp.databinding.FragmentDetailCoinBinding
import com.example.cryptocoinapp.domain.model.CryptoCoin
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
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

                val lineEntries = listOf(
                    Entry(0f, 10f),
                    Entry(1f, 20f),
                    Entry(1.2f, 15f),
                    Entry(2f, 30f)
                ) // Replace with your own data

                val dataSet = LineDataSet(lineEntries, "${it.name}")
                val lineData = LineData(dataSet)
                graphicView.data = lineData
                graphicView.invalidate()
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