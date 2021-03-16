package ru.netology.valuteapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import ru.netology.valuteapp.R
import ru.netology.valuteapp.adapter.ValuteAdapter
import ru.netology.valuteapp.databinding.FragmentFeedBinding
import ru.netology.valuteapp.viewmodel.ValuteViewModel

class FeedFragment : Fragment() {
    private val viewModel: ValuteViewModel by viewModels(ownerProducer = ::requireParentFragment)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFeedBinding.inflate(layoutInflater)
        val adapter = ValuteAdapter()
        binding.rvValuteList.adapter = adapter
        binding.swipeRefreshLayout.setOnRefreshListener(viewModel::refresh)
        viewModel.state.observe(viewLifecycleOwner) { model ->
            binding.pbProgress.isVisible = model.loading
            binding.swipeRefreshLayout.isRefreshing = model.refreshing
        }
        viewModel.data.observe(viewLifecycleOwner, adapter::submitList)
        return binding.root
    }

}