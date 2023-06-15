package com.example.mnotes.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceManager
import android.util.Log
import com.example.mnotes.MainActivity
import com.example.mnotes.R
import com.example.mnotes.databinding.ActivitySplashScreenBinding
import com.example.mnotes.ui.dashboard.DashboardFragment
import com.example.mnotes.ui.login.LoginActivity

class SplashScreen : AppCompatActivity() {

    private lateinit var binding : ActivitySplashScreenBinding
    private val SPLASH_TIME_OUT = 2000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        Handler().postDelayed({
            val id = PreferenceManager.getDefaultSharedPreferences(this).getString("PREF_TOKEN", null)
            Log.d("msgSplash", "pesanSplash = {$id}")

            val intent = if (id != null) {
                Log.d("pesan", "token = {$id}")
                Intent(this, MainActivity::class.java)
            }else{
                Log.d("pesan", "token = kosong brow")
                Intent(this, LoginActivity::class.java)
            }
            startActivity(intent)
            finish()
        }, SPLASH_TIME_OUT)
    }
}