package com.example.mnotes.model

import com.google.gson.annotations.SerializedName

data class SignInResponse(

	@field:SerializedName("data")
	val data: SignIn? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)


