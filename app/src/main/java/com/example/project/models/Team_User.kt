package com.example.project.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Team_Player")
data class Team_User(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "user_id") var user_id: Int,
    @ColumnInfo(name = "team_id") var team_id: Int
)