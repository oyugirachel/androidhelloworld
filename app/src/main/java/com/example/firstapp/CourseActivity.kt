package com.example.firstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_course.*


class CourseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course)

        var courseList = listOf<Course>(
            Course("89", "Ruby", "ruby34", "Betty Njambi", "Python Intro"),
            Course("100", "Kotlin", "KOT453", "John Owuor", "Kotlin Class"),
            Course("343", "Python", "py10", "John Mwai", "OOP classes"),
            Course("900", "React", "React34", "Purity", "MamaMboga"),
            Course("789", "Navigating", "Nav34", "Betty Njambi", "Python Intro"),
            Course("100", "Kotlin", "KOT453", "John Owuor", "Kotlin Class"),
            Course("343", "Python", "py10", "John Mwai", "OOP classes")

        )
        rvCouses.layoutManager = LinearLayoutManager(baseContext)
        rvCouses.adapter = CoursesRecyclerViewAdapter(courseList)
    }
}