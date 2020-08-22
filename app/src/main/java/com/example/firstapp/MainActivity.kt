package com.example.firstapp

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.etPassword
import kotlinx.android.synthetic.main.activity_main.tvRegister
import kotlinx.android.synthetic.main.activity_registrationactivity.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvRegister.setOnClickListener {
            val intent = Intent(baseContext, Registrationactivity::class.java)
            startActivity(intent)
        }
        btnLogin.setOnClickListener {
            var userName = etUserName.text.toString()
            var password = etPassword.text.toString()

            if(userName.isBlank() || userName.isEmpty()){
                etUserName.error="Username  is required"
            }
            if(password.isBlank() || password.isEmpty()){
                etPassword.error="Password is required"
            }
            progressBar.max=1000
            val currentProgress=600
            ObjectAnimator.ofInt(progressBar,"progress",currentProgress)
                .setDuration(20000)
                .start()


            var requestBody = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("email", userName)

                .addFormDataPart("password", password)
                .build()
            loginStudents(requestBody)
            Toast.makeText(baseContext, password, Toast.LENGTH_LONG).show()


        }

    }
    fun loginStudents(requestBody: RequestBody) {
        val apiClient = ApiClient.buildService(LoginApiInterface::class.java)
        val loginCall = apiClient.loginStudent(requestBody)
        loginCall.enqueue(object : Callback<LoginResponse> {
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                if (response.isSuccessful) {
                    Toast.makeText(baseContext, response.body()?.message, Toast.LENGTH_LONG)
                        .show()
                    var accessToken = response.body()?.accessToken
                    var sharedPreferences = PreferenceManager.getDefaultSharedPreferences(baseContext)
                    var editor = sharedPreferences.edit()
                    editor.putString("ACCESS_TOKEN_KEY", accessToken)
                    editor.apply()
                    val intent = Intent(baseContext, CourseActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(
                        baseContext,
                        response.errorBody().toString(),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        })
    }
}
