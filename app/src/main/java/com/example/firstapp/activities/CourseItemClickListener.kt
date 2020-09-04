package com.example.firstapp.activities

import com.example.firstapp.models.Course

interface CourseItemClickListener {
    fun onItemClick(course: Course)
}