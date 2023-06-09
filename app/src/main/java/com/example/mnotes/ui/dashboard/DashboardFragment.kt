package com.example.mnotes.ui.dashboard

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mnotes.R
import com.example.mnotes.databinding.FragmentDashboardBinding
import com.example.mnotes.model.Order
import com.example.mnotes.ui.profile.ProfileActivity

class DashboardFragment : Fragment() {

    private lateinit var todayOrderAdapter: TodayOrderAdapter
    private lateinit var todayOrderViewModel: TodayOrderViewModel

    private lateinit var yesterdayOrderViewModel : YesterdayViewModel
    private lateinit var yesterdatOrderAdapter: YesterdayAdapter

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private lateinit var sharedPreferences: SharedPreferences
    private var fullname: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivAvatar.setOnClickListener {
            val intent = Intent(requireContext(), ProfileActivity::class.java)
            startActivity(intent)
        }

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        fullname = sharedPreferences.getString("PREF_FULLNAME", "")
        todayOrderViewModel = ViewModelProvider(this)[TodayOrderViewModel::class.java]
        yesterdayOrderViewModel = ViewModelProvider(this)[YesterdayViewModel::class.java]

        binding.tvName.text = fullname

        setUpTodayOrder()
        observeTodayOrder()
        SetUpYesterdayOrder()
        observeYesterdayOrder()
//        todayOrderViewModel.getListToday()
        yesterdayOrderViewModel.getListYesterday()

//
    }

    private fun setUpTodayOrder() {
        binding.rvTodayOrder.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        todayOrderAdapter = TodayOrderAdapter(null)
        binding.rvTodayOrder.adapter = todayOrderAdapter
    }

    private fun observeTodayOrder() {
        todayOrderViewModel.listOrder.observe(viewLifecycleOwner) { orderResponse ->
            val orders = orderResponse?.data
            if (orders.isNullOrEmpty()){
                binding.tvTodayOrderEmpty.visibility = View.VISIBLE
            }else{
                binding.tvTodayOrderEmpty.visibility = View.INVISIBLE
                val reversedList = orders.reversed() // Reverse the list
                todayOrderAdapter.listOrder = reversedList
                todayOrderAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun SetUpYesterdayOrder() {
        binding.rvYesterday.layoutManager = LinearLayoutManager(context)
        yesterdatOrderAdapter = YesterdayAdapter(null)
        binding.rvYesterday.adapter = yesterdatOrderAdapter
    }

    private fun observeYesterdayOrder(){
        yesterdayOrderViewModel.listOrder.observe(viewLifecycleOwner) {orderResponse ->
            val orders = orderResponse?.data
            if (orders.isNullOrEmpty()){
                binding.tvYesterdayOrderEmpty.visibility = View.VISIBLE
            }else{
                binding.tvYesterdayOrderEmpty.visibility = View.INVISIBLE
                yesterdatOrderAdapter.listOrder = orderResponse?.data
                yesterdatOrderAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        todayOrderViewModel.getListToday()
    }
}

