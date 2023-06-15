package com.example.mnotes.ui.register

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mnotes.model.SignInResponse
import com.example.mnotes.model.SignUpResponse
import com.example.mnotes.model.User
import com.example.mnotes.networks.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/** Raihan Chaira on 5/10/2023
 * raihanchaira21@gmail.com
 */
class RegisterViewModel : ViewModel() {
    val registrationResult = MutableLiveData<SignUpResponse>()

    fun registerUser(user: User) {
        registrationResult.value = null
        val call = ApiConfig.getApiService().RegisterUser(user.fullname, user.email, user.username, user.phoneNumber, user.password)

        call.enqueue(object : Callback<SignUpResponse> {
            override fun onResponse(
                call: Call<SignUpResponse>,
                response: Response<SignUpResponse>
            ) {
                if (response.isSuccessful) {
                    registrationResult.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                Log.e(ContentValues.TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }
}