package com.example.project.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Game_User")
data class Game_User(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "user_id") var user_id: Int,
    @ColumnInfo(name = "game_id") var game_id: Int,
    @ColumnInfo(name = "team_id") var team_id: Int,
    @ColumnInfo(name = "isThere") var isThere: Boolean
)