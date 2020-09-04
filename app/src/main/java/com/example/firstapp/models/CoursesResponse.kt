package com.example.firstapp.models

import com.example.firstapp.models.Course
import com.google.gson.annotations.SerializedName

data class CoursesResponse(
    @SerializedName("courses") var courses: List<Course>
)