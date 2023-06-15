package com.example.mnotes.model

import com.google.gson.annotations.SerializedName

data class AddOrderResponse(
	@field:SerializedName("status")
	val status: String? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("data")
	val data: AddOrderResult? = null,
)


