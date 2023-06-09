package com.example.mnotes.ui.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mnotes.MainActivity
import com.example.mnotes.databinding.ActivityLoginBinding
import com.example.mnotes.ui.register.RegisterActivity

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    lateinit var viewModel : LoginViewModel
    private lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        sharedPreferences = getSharedPreferences("userId", Context.MODE_PRIVATE)

        binding.tvToRegister.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }

        viewModel = ViewModelProvider(
            this, ViewModelProvider.NewInstanceFactory()
        )[LoginViewModel::class.java]



        loginUser()
        observeLoginResult()

    }

    private fun loginUser() {
        showLoading(false)

        binding.btLogin.setOnClickListener {
            val username = binding.edLoginName.text.toString().trim()
            val password = binding.edLoginPass.text.toString().trim()

            val errorMessages = mutableListOf<String>()

            if (username.isEmpty()) {
                errorMessages.add("Please enter a username")
                binding.edLoginName.error = "Please enter a username"
            } else {
                binding.edLoginName.error = null
            }

            if (password.isEmpty()) {
                errorMessages.add("Please enter a password")
                binding.edLoginPass.error = "Please enter a password"
            } else {
                binding.edLoginPass.error = null
            }

            if (errorMessages.isEmpty()) {
                binding.btLogin.isEnabled = false
                showLoading(true)
                viewModel.loginUser(username, password)
            } else {
                val errorMessage = errorMessages.joinToString("\n")
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun observeLoginResult() {
        viewModel.loginResult.observe(this, Observer { result ->
            showLoading(false)
            binding.btLogin.isEnabled = true

            if (result != null) {
                if (result.status == "success") {
                    Log.d("msg", "Fullname: ${result.data?.phoneNumber}")
                    Log.d("msg", "Fullname: ${result.data?.email}")
                    saveId(result.data?._id)
                    saveUsername(result.data?.username)
                    saveFullName(result.data?.fullname)
                    saveEmail(result.data?.email)
                    savePhone(result.data?.phoneNumber)

                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, result.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.INVISIBLE
        }
    }

    private fun saveId(token: String?) {
        PreferenceManager.getDefaultSharedPreferences(this@LoginActivity)
            .edit()
            .putString("PREF_TOKEN", token)
            .apply()
    }

    private fun saveUsername(username: String?) {
        PreferenceManager.getDefaultSharedPreferences(this@LoginActivity)
            .edit()
            .putString("PREF_USERNAME", username)
            .apply()
    }

    private fun saveFullName(fullname: String?) {
        PreferenceManager.getDefaultSharedPreferences(this@LoginActivity)
            .edit()
            .putString("PREF_FULLNAME", fullname)
            .apply()
    }

    private fun saveEmail(email: String?) {
        PreferenceManager.getDefaultSharedPreferences(this@LoginActivity)
            .edit()
            .putString("PREF_EMAIL", email)
            .apply()
    }

    private fun savePhone(phone: Long?) {
        PreferenceManager.getDefaultSharedPreferences(this@LoginActivity)
            .edit()
            .putLong("PREF_PHONE", phone ?: 0L)
            .apply()
    }

    private fun savePass(pass : String?) {
        PreferenceManager.getDefaultSharedPreferences(this@LoginActivity)
            .edit()
            .putString("PREF_PASS", pass)
            .apply()
    }
}