package ru.netology.valuteapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.valuteapp.R
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
        holder.bind(valute, holder.itemView.context)
    }
}

class ValuteViewHolder(
    private val binding: ValuteCardBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(valute: Valute, context: Context) {
        binding.apply {
            tvValuteId.text = context.getString(R.string.valute_id, valute.id)
            tvValuteNumcode.text = context.getString(R.string.valute_numCode, valute.numCode)
            tvValuteCharcode.text = context.getString(R.string.valute_charCode, valute.charCode)
            tvValuteNominal.text =
                context.getString(R.string.valute_nominal, valute.nominal.toString())
            tvValuteName.text = context.getString(R.string.valute_name, valute.name)
            tvValuteValue.text = context.getString(R.string.valute_value, valute.value)
            tvValutePrevious.text = context.getString(R.string.valute_previous, valute.previous)
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
