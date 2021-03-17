package ru.netology.valuteapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.netology.valuteapp.databinding.FragmentConverterBinding

class ConverterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentConverterBinding.inflate(layoutInflater)
        val name = arguments?.let { ConverterFragmentArgs.fromBundle(it).name.trim() }
        val nominal = arguments?.let { ConverterFragmentArgs.fromBundle(it).nominal }
        val value = arguments?.let { ConverterFragmentArgs.fromBundle(it).value.trim() }
        val previous = arguments?.let { ConverterFragmentArgs.fromBundle(it).previous.trim() }
        binding.tvTest.text = name + " " + nominal + " " + value + " " + previous
        return binding.root
    }
}