package com.example.firstapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvRegister.setOnClickListener{
            val intent= Intent(baseContext,Registrationactivity::class.java)
            startActivity(intent)
        }
        btnLogin.setOnClickListener {
            var UserName=etUserName.text.toString()
            var Password= etPassword.text.toString()
            Toast.makeText(baseContext,Password,Toast.LENGTH_LONG).show()


        }
    }
}
