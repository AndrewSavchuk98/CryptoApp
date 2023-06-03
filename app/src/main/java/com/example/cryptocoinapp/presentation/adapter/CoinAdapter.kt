package com.example.cryptocoinapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.cryptocoinapp.databinding.CoinItemBinding
import com.example.cryptocoinapp.domain.model.CryptoCoin

class CoinAdapter(private val listener: OnCoinClickListener) :
    ListAdapter<CryptoCoin, CoinAdapter.CoinViewHolder>(CoinDiffUtil()) {

    interface OnCoinClickListener {
        fun onCoinClick(coin: CryptoCoin)
    }


    class CoinViewHolder(private val binding: CoinItemBinding) : ViewHolder(binding.root) {
        fun bind(coin: CryptoCoin) {
            with(binding) {
                iconImageView.load(coin.imageUrl)
                nameTextView.text = coin.name
                priceTextview.text = "$ ${coin.price}"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val binding = CoinItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CoinViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {

        val coin = currentList[position]
        holder.bind(coin)
        holder.itemView.setOnClickListener {
            listener.onCoinClick(coin = coin)
        }
    }
}

class CoinDiffUtil : DiffUtil.ItemCallback<CryptoCoin>() {
    override fun areItemsTheSame(oldItem: CryptoCoin, newItem: CryptoCoin): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: CryptoCoin, newItem: CryptoCoin): Boolean {
        return oldItem.name == newItem.name
    }

}