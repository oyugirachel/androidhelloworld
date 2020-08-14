package com.example.firstapp

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("access_token")var accessToken:String,
    @SerializedName("message")var message:String

)