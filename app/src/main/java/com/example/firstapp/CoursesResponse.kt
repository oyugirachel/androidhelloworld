package com.example.firstapp

import com.google.gson.annotations.SerializedName

data class CoursesResponse(
    @SerializedName("courses") var courses: List<Course>
)