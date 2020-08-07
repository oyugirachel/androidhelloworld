package com.example.firstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_course.*

data class Courses(val course_id: Int, val course_name: String, val course_code: Int, val instructor:String, val description:String)


class CourseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course)

        rvCouses.layoutManager = LinearLayoutManager(baseContext)
        val coursesRecyclerViewAdapter = CoursesRecyclerViewAdapter(coursesList = listOf(

            Courses(56,"Python",113,"James Mwai","Hardworking"),
            Courses(56,"Kotlin",112,"John Owuor","Hardworking"),
            Courses(56,"Javascript",111,"Purity Maina","Hardworking"),
            Courses(56,"Html/Css",110,"Jeff Muthondu","Hardworking"),
            Courses(56,"React",109,"Purity maina","Hardworking"),
            Courses(56,"Navigating",108,"Vero Thamaini","Hardworking"),
            Courses(56,"Proffessional development",107,"Rodgers Owoko","Hardworking"),
            Courses(56,"Design",106,"Nyandia","Hardworking"),
            Courses(56,"Entreprenuership",105,"Kelly Murungi","Hardworking"),
            Courses(56,"Hardware Design",104,"Barre Yasin","Hardworking"),
            Courses(56,"Hardware Electronics",103,"Barre Yasin","Hardworking")


        ))
                rvCouses.adapter=coursesRecyclerViewAdapter
    }
}