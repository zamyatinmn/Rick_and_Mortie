package com.geekbrains.rickmortie.ui.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.geekbrains.rickmortie.App
import com.geekbrains.rickmortie.AppState
import com.geekbrains.rickmortie.databinding.FragmentMainBinding
import com.geekbrains.rickmortie.ui.ViewBindingFragment

class MainFragment : ViewBindingFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {
    companion object {
        fun newInstance() = MainFragment()
    }

    private var adapter: Adapter = Adapter().apply { App.instance.appComponent.inject(this) }
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java).apply {
            App.instance.appComponent.inject(this)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        viewModel.getLiveData().observe(viewLifecycleOwner, { renderData(it) })
        viewModel.getData()
    }

    private fun initRecyclerView() {
        binding.recyclerView.setHasFixedSize(true)
        setHasOptionsMenu(true)
        val layoutManager = GridLayoutManager(
            requireContext(), 2, GridLayoutManager.VERTICAL, false
        )
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter

        binding.refresh.setOnRefreshListener {
            adapter.clear()
            viewModel.getData()
            binding.refresh.isRefreshing = false
        }
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            AppState.Loading -> {
                binding.loading.visibility = View.VISIBLE
            }
            is AppState.Success -> {
                binding.loading.visibility = View.GONE
                adapter.setData(appState.result)
            }
        }
    }
}
