package com.example.cryptocoinapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.cryptocoinapp.databinding.MarketCoinItemBinding
import com.example.cryptocoinapp.domain.model.CryptoCoin

class CoinMarketAdapter(private val listener: CoinAdapter.OnCoinClickListener) :
    ListAdapter<CryptoCoin, CoinMarketAdapter.CoinMarketViewHolder>(CoinDiffUtil()) {

    class CoinMarketViewHolder(private val binding: MarketCoinItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(coin: CryptoCoin) {
            with(binding) {
                iconImageView.load(coin.imageUrl)
                nameTextView.text = coin.name
                priceTextview.text = "$ ${coin.price}"

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinMarketViewHolder {
        val binding = MarketCoinItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CoinMarketViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinMarketViewHolder, position: Int) {

        val coin = currentList[position]
        holder.bind(coin)
        holder.itemView.setOnClickListener {
            listener.onCoinClick(coin = coin)
        }
    }
}
