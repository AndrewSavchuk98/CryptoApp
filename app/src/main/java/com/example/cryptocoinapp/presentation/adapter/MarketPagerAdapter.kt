package com.example.cryptocoinapp.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.cryptocoinapp.presentation.screens.RecyclerFragment

class MarketPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return RecyclerFragment.newInstance(position)
    }
}