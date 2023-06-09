package com.example.mnotes.ui.profile

import android.app.Application
import android.content.ContentValues
import android.content.Context
import android.preference.PreferenceManager
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.mnotes.model.SignUpResponse
import com.example.mnotes.networks.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/** Raihan Chaira on 6/8/2023
 * raihanchaira21@gmail.com
 */
class ProfileViewModel(application: Application) : AndroidViewModel(application) {

    val updateData = MutableLiveData<SignUpResponse>()
    private val _isLoading = MutableLiveData<Boolean>()
    private val context: Context = application.applicationContext
    private val id =
        PreferenceManager.getDefaultSharedPreferences(application).getString("PREF_TOKEN", "")

    fun updateUser(userId: String?,
                   fullname: String,
                   username: String,
                   email: String,
                   phone: String,
                   password: String){
        _isLoading.value = true
        if (id?.isNotEmpty() == true){
            ApiConfig.getApiService().updateProfile(id, fullname, username, email, phone, password)
                .enqueue(object : Callback<SignUpResponse>{
                    override fun onResponse(
                        call: Call<SignUpResponse>,
                        response: Response<SignUpResponse>
                    ) {
                        _isLoading.value = false
                        if (response.isSuccessful) {
                            updateData.value = response.body()
                            Toast.makeText(
                                context,
                                "Update successful",
                                Toast.LENGTH_SHORT
                            ).show()
                         }
                    }

                    override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                        Log.e(ContentValues.TAG, "onFailure: ${t.message.toString()}")
                    }
                })
        }
    }
}