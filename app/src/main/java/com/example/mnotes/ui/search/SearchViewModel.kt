package com.example.mnotes.ui.search

import android.app.Application
import android.content.ContentValues
import android.preference.PreferenceManager
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mnotes.model.OrderResponse
import com.example.mnotes.networks.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Query

/** Raihan Chaira on 6/8/2023
 * raihanchaira21@gmail.com
 */
class SearchViewModel (application: Application) : AndroidViewModel(application) {

    val listOrder = MutableLiveData<OrderResponse>()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val id =
        PreferenceManager.getDefaultSharedPreferences(application).getString("PREF_TOKEN", "")

    fun getSearch(query: String) {
        _isLoading.value = true
        if (id?.isNotEmpty() == true) {
            Log.d("MSGMODEL", "pesan = {$id}")
            ApiConfig.getApiService().Search(id,query)
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