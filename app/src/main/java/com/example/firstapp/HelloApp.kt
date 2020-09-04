package com.example.firstapp

import android.app.Application
import com.facebook.stetho.Stetho

class HelloApp: Application() {
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }
}
