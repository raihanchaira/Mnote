package com.example.mnotes.model

import com.google.gson.annotations.SerializedName

/** Raihan Chaira on 5/24/2023
 * raihanchaira21@gmail.com
 */
data class OrderResponse(
    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("data")
    val data: List<Order>? = null,
)