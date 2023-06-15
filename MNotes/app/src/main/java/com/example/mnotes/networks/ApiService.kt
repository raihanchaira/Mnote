package com.example.mnotes.networks

import com.example.mnotes.model.AddOrderResponse
import com.example.mnotes.model.OrderResponse
import com.example.mnotes.model.ResponseOneUser
import com.example.mnotes.model.SignInResponse
import com.example.mnotes.model.SignUpResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query
import retrofit2.http.Field as Field1

/** Raihan Chaira on 5/10/2023
 * raihanchaira21@gmail.com
 */
interface ApiService {
    @FormUrlEncoded
    @POST("auth/signin")
    fun SignInUser(
        @Field1("username") username : String,
        @Field1("password") password : String
    ) : Call<SignInResponse>

    @FormUrlEncoded
    @POST("auth/signup")
    fun RegisterUser(
        @Field1("fullname") fullname : String,
        @Field1("email") email : String,
        @Field1("username") username : String,
        @Field1("phoneNumber") phoneNumber : String,
        @Field1("password") password : String,
    ): Call<SignUpResponse>

    @FormUrlEncoded
    @POST("orders/order/order")
    fun postAddOrder(
        @Field1("userId") userId: String,
        @Field1("name") name : String,
        @Field1("amount") amount : String
        ) : Call<AddOrderResponse>

    @FormUrlEncoded
    @POST("orders/order")
    fun getAllOrders(
        @Field1("userId") userId: String
    ): Call<OrderResponse>

    @FormUrlEncoded
    @POST("orders/order/yesterday")
    fun getYesterdayOrder(
        @Field1("userId") userId: String
    ) : Call<OrderResponse>

    @FormUrlEncoded
    @POST("orders/order/search")
    fun Search(
        @Field1("userId") userId : String,
        @Field1("name") name : String
    ) : Call<OrderResponse>

    //update profile
    @FormUrlEncoded
    @PUT("users/user")
    fun updateProfile(
        @Field1("userId") userId : String,
        @Field1("fullname") fullname : String,
        @Field1("username") username : String,
        @Field1("email") email : String,
        @Field1("phone") phone : String,
        @Field1("password") password : String
    ) : Call<SignUpResponse>

    @FormUrlEncoded
    @GET("users/user")
    fun getOneUser(
        @Field1("userId") userId : String,
    ) : Call<ResponseOneUser>

    @FormUrlEncoded
    @POST("orders/order")
    fun getSummary(
        @Field1("userId") userId : String,
        @Field1("date") date : String
    ) : Call<OrderResponse>
}