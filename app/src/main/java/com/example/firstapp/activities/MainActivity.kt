package com.example.firstapp.activities

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.widget.Toast
import com.example.firstapp.R
import com.example.firstapp.api.ApiClient
import com.example.firstapp.api.LoginApiInterface
import com.example.firstapp.models.LoginResponse
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.etPassword
import kotlinx.android.synthetic.main.activity_main.tvRegister
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

            var error=false


            if(userName.isBlank() || userName.isEmpty()){
                etUserName.error="Username  is required"
            }
            if(password.isBlank() || password.isEmpty()){
                etPassword.error="Password is required"
            }



            var requestBody = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("email", userName)

                .addFormDataPart("password", password)
                .build()

            if (!error){
                pbLogin.visibility= View.VISIBLE
                loginStudents(requestBody)
            }
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
                    pbLogin.visibility=View.GONE
                    Toast.makeText(baseContext, response.body()?.message, Toast.LENGTH_LONG)
                        .show()
                    var accessToken = response.body()?.accessToken
                    val studentId=response.body()?.studentId
                    var sharedPreferences = PreferenceManager.getDefaultSharedPreferences(baseContext)
                    var editor = sharedPreferences.edit()
                    editor.putString("ACCESS_TOKEN_KEY", accessToken)
                    editor.putString("STUDENT_ID_KEY", studentId)
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
