package com.example.mnotes.ui.login

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mnotes.model.SignInResponse
import com.example.mnotes.networks.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/** Raihan Chaira on 5/10/2023
 * raihanchaira21@gmail.com
 */

class LoginViewModel : ViewModel() {
    private val userId: MutableLiveData<String> = MutableLiveData()

    val loginResult: MutableLiveData<SignInResponse> = MutableLiveData()

    fun loginUser(username: String, password: String) {
        val call: Call<SignInResponse> = ApiConfig.getApiService().SignInUser(username, password)
        call.enqueue(object : Callback<SignInResponse> {
            override fun onResponse(call: Call<SignInResponse>, response: Response<SignInResponse>) {
                if (response.isSuccessful) {
                    val signInResponse = response.body()
                    val userId = signInResponse?.data?._id
                    if (userId != null && response.isSuccessful) {
                        loginResult.postValue(response.body())
                        this@LoginViewModel.userId.value = userId
                    }
                }
            }

            override fun onFailure(call: Call<SignInResponse>, t: Throwable) {
                Log.e(ContentValues.TAG, "onFailure : ${t.message.toString()}")
            }
        })
    }
}


