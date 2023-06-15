package com.example.mnotes.ui.addorder


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.mnotes.R
import com.example.mnotes.databinding.FragmentAddOrderBinding
import com.example.mnotes.ui.dashboard.TodayOrderViewModel
import com.example.mnotes.ui.dashboard.YesterdayViewModel


class AddOrderFragment : DialogFragment() {

    private var _binding: FragmentAddOrderBinding? = null
    private val binding get() = _binding!!
    private lateinit var addOrderViewModel: AddOrderViewModel
    private lateinit var todayOrderViewModel: TodayOrderViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddOrderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addOrderViewModel = ViewModelProvider(this).get(AddOrderViewModel::class.java)
        todayOrderViewModel = ViewModelProvider(this).get(TodayOrderViewModel::class.java)


        // Access views using binding
        binding.btAdd.setOnClickListener {
            val itemName = binding.edItemName.text.toString()
            val amount = binding.edAmount.text.toString()
            addOrderViewModel.addOrder(itemName, amount)

            todayOrderViewModel.getListToday()
            dismiss()
        }

        binding.btClose.setOnClickListener {
            dismiss()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomDialogTheme);
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
