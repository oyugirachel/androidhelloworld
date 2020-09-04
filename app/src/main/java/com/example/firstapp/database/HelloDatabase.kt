package com.example.firstapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.firstapp.models.Course

class HelloDatabase {
    @Database(entities = arrayOf(Course::class), version = 1)
    abstract class HelloDatabase: RoomDatabase() {
        abstract fun courseDao(): CourseDao
    }
}