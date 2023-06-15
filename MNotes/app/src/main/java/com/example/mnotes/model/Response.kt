package com.example.mnotes.model

import com.google.gson.annotations.SerializedName

data class ResponseOneUser(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)


