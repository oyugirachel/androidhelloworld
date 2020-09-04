package com.example.firstapp.api

import com.example.firstapp.models.LoginResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApiInterface {
    @POST("login")
    fun loginStudent(@Body requestBody: RequestBody):Call<LoginResponse>
}