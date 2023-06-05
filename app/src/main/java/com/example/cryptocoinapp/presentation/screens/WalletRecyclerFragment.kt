package com.example.cryptocoinapp.presentation.screens

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.cryptocoinapp.R
import com.example.cryptocoinapp.databinding.FragmentRecyclerBinding
import com.example.cryptocoinapp.domain.model.CryptoCoin
import com.example.cryptocoinapp.presentation.adapter.CoinAdapter
import com.example.cryptocoinapp.presentation.adapter.CoinMarketAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WalletRecyclerFragment: Fragment(R.layout.fragment_recycler), CoinAdapter.OnCoinClickListener {

    private lateinit var binding: FragmentRecyclerBinding
    private val viewModel by viewModels<HomeViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRecyclerBinding.bind(view)


        val adapter = CoinMarketAdapter(this)
        binding.recyclerView.adapter = adapter

        viewModel.coinsLiveData.observe(viewLifecycleOwner) {
            adapter.submitList(
                when (arguments?.getInt(WALLET_TAB_POSITION_KEY)) {
                    0 -> it // Data for tab 1
                    1 -> it.asReversed() // Data for tab 2
                    else -> emptyList() // Default case
                }
            )
        }
    }

    companion object {
        private const val WALLET_TAB_POSITION_KEY = "wallet_tab_position"

        fun newInstance(tabPosition: Int): Fragment {
            val fragment = RecyclerFragment()
            fragment.arguments = bundleOf(WALLET_TAB_POSITION_KEY to tabPosition)
            return fragment
        }
    }

    override fun onCoinClick(coin: CryptoCoin) {
        findNavController().navigate(R.id.detailCoinFragment, DetailCoinFragment.putArgs(coin))
    }
}