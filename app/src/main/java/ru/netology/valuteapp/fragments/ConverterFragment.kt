package ru.netology.valuteapp.fragments

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import ru.netology.valuteapp.R
import ru.netology.valuteapp.databinding.FragmentConverterBinding
import ru.netology.valuteapp.viewmodel.ValuteViewModel

class ConverterFragment : Fragment() {
    private val viewModel: ValuteViewModel by viewModels(ownerProducer = ::requireParentFragment)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentConverterBinding.inflate(layoutInflater)
        val name = arguments?.let { ConverterFragmentArgs.fromBundle(it).name.trim() }
        val nominal = arguments?.let { ConverterFragmentArgs.fromBundle(it).nominal } ?: 1
        val value = arguments?.let { ConverterFragmentArgs.fromBundle(it).value.trim() }.orEmpty()
        binding.tvValueName.text = name
        binding.fabBtnSave.setOnClickListener {
            with(binding.etInputValue) {
                if (TextUtils.isEmpty(text)) {
                    Toast.makeText(
                        context,
                        context.getString(R.string.error_empty),
                        Toast.LENGTH_LONG
                    ).show()
                    return@setOnClickListener
                }
                val result = viewModel.convert(
                    value = value,
                    nominal = nominal,
                    binding.etInputValue.text.toString()
                )
                binding.tvOutConvert.text = context.getString(R.string.label_result, result)
            }
        }
        return binding.root
    }
}