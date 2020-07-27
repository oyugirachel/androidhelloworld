package com.example.firstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.etPassword
import kotlinx.android.synthetic.main.activity_main.tvRegister
import kotlinx.android.synthetic.main.activity_registrationactivity.*

class Registrationactivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrationactivity)
        tvRegister.setOnClickListener {
            var FirstName=etFirstName.text.toString()
            var Password= etPassword.text.toString()
            var LastName=etLastName.text.toString()
            var PhoneNumber=etPhoneNumber.text.toString()
            var ConfirmPassword=etPassword.text.toString()
            Toast.makeText(baseContext,Password, Toast.LENGTH_LONG).show()


        }
    }
}
