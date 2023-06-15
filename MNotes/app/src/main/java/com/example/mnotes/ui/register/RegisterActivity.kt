package com.example.mnotes.ui.register

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mnotes.databinding.ActivityRegisterBinding
import com.example.mnotes.model.User
import com.example.mnotes.ui.login.LoginActivity

class RegisterActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegisterBinding
    lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        viewModel = ViewModelProvider(
            this, ViewModelProvider.NewInstanceFactory()
        )[RegisterViewModel::class.java]


        binding.tvToRegister.setOnClickListener {
            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(intent)
        }
        register()

    }

    private fun register() {
        showLoading(false)
        binding.btnRegister.setOnClickListener {
            val name = binding.edRegisName.text.toString().trim()
            val username = binding.edRegisUsername.text.toString().trim()
            val email = binding.edRegisEmail.text.toString().trim()
            val phoneNumber = binding.edRegisPhone.text.toString().trim()
            val password = binding.edRegisPass.text.toString().trim()
            val errorMessages = mutableListOf<String>()
            if (name.isEmpty()) {
                errorMessages.add("Please enter a name")
                binding.edRegisName.requestFocus()
            }
            if (username.isEmpty()) {
                errorMessages.add("Please enter a Username")
                binding.edRegisUsername.requestFocus()
            }
            if (email.isEmpty()) {
                errorMessages.add("Please enter an Email")
                binding.edRegisEmail.requestFocus()
            }
            if (phoneNumber.isEmpty()) {
                errorMessages.add("Please enter a Phone")
                binding.edRegisPhone.requestFocus()
            }
            if (password.isEmpty()) {
                errorMessages.add("Please enter a Password")
                binding.edRegisPass.requestFocus()
            }

            if (errorMessages.isEmpty()) {
                binding.btnRegister.isEnabled = false
                showLoading(true)
                val user = User(name, username, email, phoneNumber, password)
                viewModel.registerUser(user)
            } else {
                val errorMessage = errorMessages.joinToString("\n")
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.registrationResult.observe(this, Observer { result ->
            if (result != null) {
                showLoading(false)
                binding.btnRegister.isEnabled = true
                if (result.status == "success") {
                    Toast.makeText(this, "Registration success!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                    startActivity(intent)
                    showLoading(false)
                    finish()
                } else {
                    showLoading(false)
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
}