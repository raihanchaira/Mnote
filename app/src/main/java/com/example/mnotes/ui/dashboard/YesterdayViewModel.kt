package com.example.mnotes.ui.dashboard

import android.app.Application
import android.content.ContentValues
import android.preference.PreferenceManager
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mnotes.model.AddOrderResponse
import com.example.mnotes.model.OrderResponse
import com.example.mnotes.networks.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/** Raihan Chaira on 6/1/2023
 * raihanchaira21@gmail.com
 */
class YesterdayViewModel(application: Application) : AndroidViewModel(application) {

    val listOrder = MutableLiveData<OrderResponse>()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val id =
        PreferenceManager.getDefaultSharedPreferences(application).getString("PREF_TOKEN", "")

    fun getListYesterday() {
        _isLoading.value = true
        if (id?.isNotEmpty() == true) {
            Log.d("MSGMODEL", "pesan = {$id}")
            ApiConfig.getApiService().getYesterdayOrder(id)
                .enqueue(object : Callback<OrderResponse> {
                    override fun onResponse(
                        call: Call<OrderResponse>,
                        response: Response<OrderResponse>
                    ) {
                        _isLoading.value = false
                        if (response.isSuccessful) {
                            listOrder.value = response.body()
                        }
                    }

                    override fun onFailure(call: Call<OrderResponse>, t: Throwable) {
                        _isLoading.value = false
                        Log.e(ContentValues.TAG, "onFailure: ${t.message.toString()}")
                    }

                })
        }
    }
}
