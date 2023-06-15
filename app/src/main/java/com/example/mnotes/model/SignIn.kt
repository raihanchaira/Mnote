package com.example.mnotes.model

import com.google.gson.annotations.SerializedName

/** Raihan Chaira on 5/25/2023
 * raihanchaira21@gmail.com
 */
data class SignIn(

    @field:SerializedName("phoneNumber")
    val phoneNumber: Long? = null,

    @field:SerializedName("_id")
    val _id: String? = null,

    @field:SerializedName("fullname")
    val fullname: String? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("username")
    val username: String? = null
)
