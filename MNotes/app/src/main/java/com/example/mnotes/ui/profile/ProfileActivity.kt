package com.example.mnotes.ui.profile

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.mnotes.MainActivity
import com.example.mnotes.databinding.ActivityProfileBinding
import com.example.mnotes.ui.login.LoginActivity

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    private var fullname: String? = null
    private var username: String? = null
    private var email: String? = null
    private var phoneNumber: Long = 0L

    private var id: String? = null


    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var profileViewModel: ProfileViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        profileViewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        id = sharedPreferences.getString("PREF_TOKEN", "")

        binding.toolbar.btLogout.setOnClickListener {
            sharedPreferences.edit().remove("PREF_TOKEN").apply()
            startActivity(Intent(this@ProfileActivity, LoginActivity::class.java))
        }

        binding.toolbar.btBack.setOnClickListener {
            startActivity(Intent(this@ProfileActivity, MainActivity::class.java))
        }

        binding.btUpdate.setOnClickListener {
            val newFullname = binding.edProfileName.text.toString().trim()
            val newUsername = binding.edProfileUsername.text.toString().trim()
            val newEmail = binding.edProfileEmail.text.toString().trim()
            val newPhoneNumber = binding.edProfilePhone.text.toString().trim()
            val password = binding.edProfilePass.text.toString().trim()

            val errorMessages = mutableListOf<String>()

            if (newFullname.isEmpty()) {
                errorMessages.add("Please enter a full name")
                binding.edProfileName.error = "Please enter a full name"
            } else {
                binding.edProfileName.error = null
            }

            if (newUsername.isEmpty()) {
                errorMessages.add("Please enter a username")
                binding.edProfileUsername.error = "Please enter a username"
            } else {
                binding.edProfileUsername.error = null
            }

            if (newEmail.isEmpty()) {
                errorMessages.add("Please enter an email")
                binding.edProfileEmail.error = "Please enter an email"
            } else {
                binding.edProfileEmail.error = null
            }

            if (newPhoneNumber.isEmpty()) {
                errorMessages.add("Please enter a phone number")
                binding.edProfilePhone.error = "Please enter a phone number"
            } else {
                binding.edProfilePhone.error = null
            }

            if (password.isEmpty()) {
                errorMessages.add("Please enter a password")
                binding.edProfilePass.error = "Please enter a password"
            } else {
                binding.edProfilePass.error = null
            }

            if (errorMessages.isEmpty()) {
                binding.btUpdate.isEnabled = false
                showLoading(true)
                profileViewModel.updateUser(id, newFullname, newUsername, newEmail, newPhoneNumber, password)
            } else {
                val errorMessage = errorMessages.joinToString("\n")
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
            }
            showLoading(false)
        }


        // Initialize fullname and username after setContentView()
        fullname = sharedPreferences.getString("PREF_FULLNAME", "")
        username = sharedPreferences.getString("PREF_USERNAME", "")
        email = sharedPreferences.getString("PREF_EMAIL", "")
        phoneNumber = sharedPreferences.getLong("PREF_PHONE", 0L)

        Log.d("msg", "pesanData: $username dan $fullname dan $phoneNumber dan $email")

        // Set values as hint for corresponding EditText fields
        binding.edProfileName.hint = fullname
        binding.edProfileUsername.hint = username
        binding.edProfileEmail.hint = email
        binding.edProfilePhone.hint = phoneNumber.toString()

        binding.fullname.text = fullname
        binding.username.text = username
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.INVISIBLE
        }
    }
}