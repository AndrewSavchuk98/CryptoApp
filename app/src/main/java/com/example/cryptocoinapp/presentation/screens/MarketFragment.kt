package com.example.cryptocoinapp.presentation.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.cryptocoinapp.R
import com.example.cryptocoinapp.databinding.FragmentMarketBinding
import com.example.cryptocoinapp.domain.model.CryptoCoin
import com.example.cryptocoinapp.presentation.adapter.CoinAdapter
import com.example.cryptocoinapp.presentation.adapter.MarketPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MarketFragment : Fragment(R.layout.fragment_market) {

    private lateinit var binding: FragmentMarketBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMarketBinding.bind(view)
        val adapter = MarketPagerAdapter(this)
        binding.recyclerViewPager.adapter = adapter
        val tabLayoutMediator =
            TabLayoutMediator(binding.tabLayout, binding.recyclerViewPager) { tab, position ->
                // Set the tab titles
                tab.text = when (position) {
                    0 -> "Favorites"
                    1 -> "Spot"
                    2 -> "Futures"
                    3 -> "Zones"
                    else -> ""
                }
            }
        tabLayoutMediator.attach()
    }

}