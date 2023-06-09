package com.example.mnotes.ui.addorder

import android.app.Application
import android.content.ContentValues
import android.preference.PreferenceManager
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.mnotes.model.AddOrderResponse
import com.example.mnotes.networks.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/** Raihan Chaira on 6/1/2023
 * raihanchaira21@gmail.com
 */
class AddOrderViewModel(application: Application) : AndroidViewModel(application) {

    val order = MutableLiveData<AddOrderResponse>()

    private val id =
        PreferenceManager.getDefaultSharedPreferences(application).getString("PREF_TOKEN", "")

    fun addOrder(name: String, amount: String) {
        if (id?.isNotEmpty() == true) {
            ApiConfig.getApiService().postAddOrder(id, name, amount)
                .enqueue(object : Callback<AddOrderResponse> {
                    override fun onResponse(
                        call: Call<AddOrderResponse>,
                        response: Response<AddOrderResponse>
                    ) {
                        if (response.isSuccessful){
                            order.value = response.body()
                        }
                    }

                    override fun onFailure(call: Call<AddOrderResponse>, t: Throwable) {
                        Log.e(ContentValues.TAG, "onFailure: ${t.message.toString()}")
                    }
                })
        }
    }
}