package com.example.project.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "Game")
data class Game(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "firstTeam") var firstTeam: Int,
    @ColumnInfo(name = "secondTeam") var secondTeam: Int,
    @ColumnInfo(name = "location") var location: String,
    @ColumnInfo(name = "date") var date: Date
)