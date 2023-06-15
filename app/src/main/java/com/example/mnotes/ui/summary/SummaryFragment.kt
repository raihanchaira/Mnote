package com.example.mnotes.ui.summary

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mnotes.R
import com.example.mnotes.databinding.FragmentSummaryBinding
import com.example.mnotes.ui.dashboard.TodayOrderAdapter
import com.example.mnotes.ui.dashboard.YesterdayAdapter

class SummaryFragment : Fragment() {

    private var _binding : FragmentSummaryBinding? = null
    private val binding get() =  _binding!!

    private lateinit var summaryViewModel: SummaryViewModel
    private lateinit var yesterdayAdapter: YesterdayAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_summary, container, false)

        // Get the support action bar
        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSummaryBinding.bind(view)

        summaryViewModel = ViewModelProvider(requireActivity())[SummaryViewModel::class.java]

        setupRecyclerView()
        observeViewModel()

        binding.apply {
            tvDate.setOnClickListener {
                val datePickerFragment = DatePickerFragment()
                val supportFragmentManager = requireActivity().supportFragmentManager

                supportFragmentManager.setFragmentResultListener(
                    "REQUEST_KEY",
                    viewLifecycleOwner
                ){
                    resultKey, bundle -> if (resultKey == "REQUEST_KEY"){
                        val date = bundle.getString("SELECTED_DATE")
                        binding.tvDate.text = date
                    if (date != null) {
                        summaryViewModel.getListDate(date)
                    } // Trigger API call with selected date
                    }
                }
                // show
                datePickerFragment.show(supportFragmentManager, "DatePickerFragment")
            }
        }
    }

    private fun setupRecyclerView() {
        yesterdayAdapter = YesterdayAdapter(null)
        binding.rvSummary.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = yesterdayAdapter
        }
    }

    private fun observeViewModel() {
        summaryViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            // Show loading indicator or handle loading state
        }

        summaryViewModel.listOrder.observe(viewLifecycleOwner) { orderResponse ->
            if (orderResponse != null && orderResponse.data?.isNotEmpty() == true) {
                // List is not null and not empty
                yesterdayAdapter.listOrder = orderResponse.data
                yesterdayAdapter.notifyDataSetChanged()
                binding.tvTodayOrderEmpty.visibility = View.GONE
            } else {
                // List is either null or empty
                yesterdayAdapter.listOrder = emptyList() // Set to empty list instead of null
                yesterdayAdapter.notifyDataSetChanged()
                binding.tvTodayOrderEmpty.visibility = View.VISIBLE
            }
        }
    }


//    override fun onResume() {
//        super.onResume()
//        // Trigger API call with previously selected date if it is not null
//        val selectedDate = binding.tvDate.text.toString()
//        if (selectedDate.isNotEmpty()) {
//            summaryViewModel.getListDate(selectedDate)
//        }
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}