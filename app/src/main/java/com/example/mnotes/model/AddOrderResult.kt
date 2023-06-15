package com.example.mnotes.model

import com.google.gson.annotations.SerializedName

/** Raihan Chaira on 5/26/2023
 * raihanchaira21@gmail.com
 */
data class AddOrderResult(
    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("price")
    val price: Int? = null,

    @field:SerializedName("date")
    val date: String? = null,

    @field:SerializedName("amount")
    val amount: Int? = null,

    @field:SerializedName("category")
    val category: String? = null,

    @field:SerializedName("user")
    val user: String? = null,

    @field:SerializedName("_id")
    val _id: String? = null,

    @field:SerializedName("__v")
    val __v: Int? = null

)
