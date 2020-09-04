package com.example.firstapp.activities

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firstapp.R
import com.example.firstapp.api.ApiClient
import com.example.firstapp.api.ApiInterface
import com.example.firstapp.models.RegistrationResponse
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.etPassword
import kotlinx.android.synthetic.main.activity_main.tvRegister
import kotlinx.android.synthetic.main.activity_registrationactivity.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Registrationactivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrationactivity)
        tvRegister.setOnClickListener {
            var firstName = etFirstName.text.toString()
            var password = etPassword.text.toString()
            var lastName = etLastName.text.toString()
            var email = etEmail.text.toString()
            var phoneNumber = etPhoneNumber.text.toString()
            var confirmPassword = etPassword.text.toString()

            var error=false

            if(firstName.isBlank() || firstName.isEmpty()){
                etFirstName.error="First Name is required"
            }
            if(lastName.isBlank() || lastName.isEmpty()){
                etLastName.error="Last Name is required"
            }
            if(email.isBlank() || email.isEmpty()){
                etEmail.error="Email is required"
            }
            if(phoneNumber.isBlank() || phoneNumber.isEmpty()){
                etPhoneNumber.error="Phone Number is required"
            }
            if(password.isBlank() || password.isEmpty()){
                etPassword.error="Password is required"
            }
            if(confirmPassword.isBlank() || confirmPassword.isEmpty()){
                etPassword.error="Confirm your password"
            }
            pbLogin.max=1000
            val currentProgress=600
            ObjectAnimator.ofInt(pbLogin,"progress",currentProgress)
                .setDuration(20000)
                .start()


            var requestBody = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("first_name", firstName)
                .addFormDataPart("last_name", lastName)
                .addFormDataPart("email", email)
                .addFormDataPart("phone_number", phoneNumber)
                .addFormDataPart("password", password)
                .build()
            if(!error){
                registerUser(requestBody)
            }

            //registerUser(requestBody)
            Toast.makeText(baseContext, password, Toast.LENGTH_LONG).show()


        }

    }
    fun registerUser(requestBody: RequestBody) {
        var apiClient = ApiClient.buildService(ApiInterface::class.java)
        var registrationCall = apiClient.registerStudent(requestBody)
        registrationCall.enqueue(object : Callback<RegistrationResponse> {
            override fun onFailure(call: Call<RegistrationResponse>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<RegistrationResponse>,
                response: Response<RegistrationResponse>
            ) {
                if (response.isSuccessful) {
                    Toast.makeText(baseContext, response.body()?.message, Toast.LENGTH_LONG)
                        .show()
                    startActivity(Intent(baseContext, MainActivity::class.java))
                } else {
                    Toast.makeText(
                        baseContext,
                        response.errorBody().toString(),
                        Toast.LENGTH_LONG
                    )
                        .show()
                }
            }
        })
    }
}
