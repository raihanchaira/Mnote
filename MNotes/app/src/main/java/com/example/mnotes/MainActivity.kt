package com.example.mnotes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mnotes.databinding.ActivityMainBinding
import com.example.mnotes.ui.addorder.AddOrderFragment
import com.example.mnotes.ui.dashboard.DashboardFragment
import com.example.mnotes.ui.faqninfo.FaqFragment
import com.example.mnotes.ui.search.SearchFragment
import com.example.mnotes.ui.summary.SummaryFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomNavView.background = null
        binding.bottomNavView.menu.getItem(2)
        replaceFragment(DashboardFragment())

        binding.fabAdd.setOnClickListener {
            val dialogFragment = AddOrderFragment()
            dialogFragment.show(supportFragmentManager, "AddOrderDialog")
        }


        binding.bottomNavView.setOnItemSelectedListener {

            when (it.itemId) {

                R.id.miDashboard -> replaceFragment(DashboardFragment())
                R.id.miSummarize -> replaceFragment(SummaryFragment())
                R.id.miSearch -> replaceFragment(SearchFragment())
                R.id.miFaq -> replaceFragment(FaqFragment())

                else -> {

                }
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }

}