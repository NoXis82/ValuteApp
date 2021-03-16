package ru.netology.valuteapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.valuteapp.databinding.ValuteCardBinding
import ru.netology.valuteapp.dto.Valute

class ValuteAdapter : ListAdapter<Valute, ValuteViewHolder>(ValuteDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ValuteViewHolder {
        val valuteView = ValuteCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ValuteViewHolder(valuteView)
    }

    override fun onBindViewHolder(holder: ValuteViewHolder, position: Int) {
        val valute = getItem(position)
        holder.bind(valute)
    }
}

class ValuteViewHolder(
    private val binding: ValuteCardBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(valute: Valute) {
        binding.apply {
            tvValuteId.text = valute.id
            tvValuteNumcode.text = valute.numCode
            tvValuteCharcode.text = valute.charCode
            tvValuteNominal.text = valute.nominal.toString()
            tvValuteName.text = valute.name
            tvValuteValue.text = valute.value
            tvValutePrevious.text = valute.previous
        }
    }
}

class ValuteDiffCallback : DiffUtil.ItemCallback<Valute>() {
    override fun areItemsTheSame(oldItem: Valute, newItem: Valute): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Valute, newItem: Valute): Boolean {
        return oldItem == newItem
    }

}
