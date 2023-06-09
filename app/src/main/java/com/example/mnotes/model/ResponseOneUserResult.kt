package com.example.mnotes.model

import com.google.gson.annotations.SerializedName

/** Raihan Chaira on 6/8/2023
 * raihanchaira21@gmail.com
 */
data class Data(
    @field:SerializedName("password")
    val password: String? = null,

    @field:SerializedName("phoneNumber")
    val phoneNumber: Long? = null,

    @field:SerializedName("__v")
    val __v: Int? = null,

    @field:SerializedName("orders")
    val orders: List<String?>? = null,

    @field:SerializedName("_id")
    val id: String? = null,

    @field:SerializedName("fullname")
    val fullname: String? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("username")
    val username: String? = null
)