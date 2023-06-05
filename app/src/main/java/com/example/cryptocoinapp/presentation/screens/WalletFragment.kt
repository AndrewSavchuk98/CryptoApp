package com.example.cryptocoinapp.presentation.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.cryptocoinapp.R
import com.example.cryptocoinapp.databinding.FragmentWalletBinding
import com.example.cryptocoinapp.presentation.adapter.MarketPagerAdapter
import com.example.cryptocoinapp.presentation.adapter.WalletPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WalletFragment : Fragment(R.layout.fragment_wallet) {

    private lateinit var binding: FragmentWalletBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWalletBinding.bind(view)

        val adapter = WalletPagerAdapter(this)
        binding.recyclerViewPager.adapter = adapter
        val tabLayoutMediator =
            TabLayoutMediator(binding.tabLayout, binding.recyclerViewPager) { tab, position ->
                // Set the tab titles
                tab.text = when (position) {
                    0 -> "Coin"
                    1 -> "NFT"
                    else -> ""
                }
            }
        tabLayoutMediator.attach()
    }
}