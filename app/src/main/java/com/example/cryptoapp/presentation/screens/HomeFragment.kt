package com.example.cryptoapp.presentation.screens

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.cryptoapp.R
import com.example.cryptoapp.databinding.FragmentHomeBinding
import com.example.cryptoapp.domain.model.CryptoCoin
import com.example.cryptoapp.presentation.adapter.CoinAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home), CoinAdapter.OnCoinClickListener {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel by viewModels<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        val adapter = CoinAdapter(this)

        binding.recyclerView.adapter = adapter
        viewModel.coinsLiveData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

    }

    override fun onCoinClick(coin: CryptoCoin) {
        findNavController().navigate(
            R.id.action_homeFragment_to_detailCoinFragment,
            DetailCoinFragment.putArgs(coin)
        )
    }
}