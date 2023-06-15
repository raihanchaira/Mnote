package com.example.mnotes.ui.faqninfo

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mnotes.MainActivity
import com.example.mnotes.R
import com.example.mnotes.databinding.ActivityDetailFaqBinding
import com.example.mnotes.model.Faq

class DetailFaqActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailFaqBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailFaqBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btBack.setOnClickListener {
            if (supportFragmentManager.backStackEntryCount > 0) {
                supportFragmentManager.popBackStack()
            } else {
                super.onBackPressed()
            }
        }

        val faq = intent.getParcelableExtra<Faq>(FaqFragment.DATA)
        val tittle = binding.tvTittle
        val field = binding.tvField
        tittle.text = faq?.tittle
        field.text = faq?.field
    }


    companion object{
        fun start(context: Context, data: Faq){
            val intentToDetail = Intent(context, DetailFaqActivity::class.java)
            intentToDetail.putExtra(FaqFragment.DATA, data)
            context.startActivity(intentToDetail)
        }
    }
}