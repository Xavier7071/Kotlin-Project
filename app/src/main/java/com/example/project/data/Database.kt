package com.example.project.data

import androidx.room.Dao
import androidx.room.Insert
import com.example.project.models.Users

@Dao
interface Database {
    @Insert
    fun insertAllUsers(users: List<Users>)
}