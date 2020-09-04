package com.example.firstapp.activities

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.firstapp.R
import com.example.firstapp.api.ApiClient
import com.example.firstapp.api.ApiInterface
import com.example.firstapp.database.HelloDatabase
import com.example.firstapp.models.Course
import com.example.firstapp.models.CoursesResponse
import kotlinx.android.synthetic.main.activity_course.*
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.room.RoomDatabase
import androidx.room.Database


class CourseActivity : AppCompatActivity(), CourseItemClickListener {
    lateinit var database: HelloDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course)
        database = Room.databaseBuilder(baseContext, HelloDatabase::class.java, "hello-db").build()
        fetchCourses()
    }

    fun fetchCourses() {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(baseContext)
        val accessToken = sharedPreferences.getString("ACCESS_TOKEN_KEY", "")

        val apiClient = ApiClient.buildService(ApiInterface::class.java)
        val coursesCall = apiClient.getCourses("Bearer " + accessToken)
        coursesCall.enqueue(object : Callback<CoursesResponse> {
            override fun onFailure(call: Call<CoursesResponse>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
                fetchCoursesFromDatabase()
            }

            override fun onResponse(call: Call<CoursesResponse>, response: Response<CoursesResponse>) {
                if (response.isSuccessful) {
                    var courseList = response.body()?.courses as List<Course>
                    Thread {
                        courseList.forEach { course ->
                            database.courseDao().insertCourse(course)
                        }
                    }.start()

                    displayCourses(courseList)
                } else {
                    Toast.makeText(baseContext, response.errorBody().toString(), Toast.LENGTH_LONG)
                        .show()
                }
            }
        })
    }

    fun fetchCoursesFromDatabase(){
        Thread{
            val courses = database.courseDao().getAllCourses()

            runOnUiThread {
                displayCourses(courses)
            }
        }.start()
    }

    fun displayCourses(courses: List<Course>){
        var coursesAdapter = CoursesRecyclerViewAdapter(courses, this)
        rvCouses.layoutManager = LinearLayoutManager(baseContext)
        rvCouses.adapter = coursesAdapter
    }

    override fun onItemClick(course: Course) {
        //obtain student id from shared preferences
        //courseId = course.courseId
        //make a post request https://github.com/owuor91/registration-api
    }
}