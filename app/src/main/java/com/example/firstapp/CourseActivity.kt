package com.example.firstapp

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_course.*
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CourseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course)
        progressBar.max=1000
        val currentProgress=600
        ObjectAnimator.ofInt(progressBar,"progress",currentProgress)
            .setDuration(20000)
            .start()


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
            }

            override fun onResponse(
                call: Call<CoursesResponse>,
                response: Response<CoursesResponse>
            ) {
                if (response.isSuccessful) {
                    var courseList = response.body()?.courses as List<Course>
                    var coursesAdapter = CoursesRecyclerViewAdapter(courseList)
                    rvCouses.layoutManager = LinearLayoutManager(baseContext)
                    rvCouses.adapter = coursesAdapter
                } else {
                    Toast.makeText(baseContext, response.errorBody().toString(), Toast.LENGTH_LONG)
                        .show()
                }
            }
        })
    }
}