package com.example.mnotes.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mnotes.R
import com.example.mnotes.databinding.FragmentSearchBinding
import com.example.mnotes.ui.dashboard.TodayOrderAdapter
import com.example.mnotes.ui.dashboard.TodayOrderViewModel
import com.example.mnotes.ui.dashboard.YesterdayAdapter
import com.example.mnotes.ui.dashboard.YesterdayViewModel

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: YesterdayAdapter
    private lateinit var searchViewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get the support action bar
        val actionBar = (activity as AppCompatActivity).supportActionBar

        // Hide the up button
        actionBar?.setDisplayHomeAsUpEnabled(false)
        searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]

        setupSearchRecycleView()
        search()
    }

    private fun setupSearchRecycleView() {
        binding.rvSearch.layoutManager = LinearLayoutManager(context)
        adapter = YesterdayAdapter(null)
        binding.rvSearch.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun search() {
        binding.searchBar.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchViewModel.getSearch(query.toString())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty()) {
                    adapter.listOrder = null
                    adapter.notifyDataSetChanged()
                }
                return true
            }
        })


        searchViewModel.listOrder.observe(viewLifecycleOwner) { result ->
            if (result != null) {
                adapter.listOrder = result.data
                adapter.notifyDataSetChanged()
            }
        }
    }
}