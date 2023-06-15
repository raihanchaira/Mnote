package com.example.mnotes.ui.faqninfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mnotes.R
import com.example.mnotes.databinding.FragmentFaqBinding
import com.example.mnotes.model.Faq

class FaqFragment : Fragment() {

    companion object {
        const val DATA = "DATA"
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var faqAdapter : FaqAdapter
    private var _binding: FragmentFaqBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFaqBinding.inflate(inflater, container, false)
        return binding.root

        // Hide the up button
        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView() {
        recyclerView = binding.rvFaq // Replace 'rvHeroes' with your actual RecyclerView ID
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val listFaq = getListFaq()
        faqAdapter = FaqAdapter(listFaq)
        recyclerView.adapter = faqAdapter

        faqAdapter.setOnItemClickCallback(object : FaqAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Faq) {
                DetailFaqActivity.start(requireContext(), data)
            }
        })
    }


    private fun getListFaq(): ArrayList<Faq> {
        val dataTitle = resources.getStringArray(R.array.data_tittle)
        val dataField = resources.getStringArray(R.array.data_field)
        val listFaq = ArrayList<Faq>()
        for (i in dataTitle.indices) {
            val faq = Faq(dataTitle[i], dataField[i])
            listFaq.add(faq)
        }
        return listFaq
    }

}