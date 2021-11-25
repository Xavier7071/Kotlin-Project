package com.example.project.data

import androidx.room.Dao
import androidx.room.Insert
import com.example.project.models.*

@Dao
interface Database {
    @Insert
    fun insertAllUsers(users: List<Users>)

    @Insert
    fun insertAllPlayers(users: List<Players>)

    @Insert
    fun insertAllCoaches(users: List<Coaches>)

    @Insert
    fun insertAllGames(users: List<Game>)

    @Insert
    fun insertAllGameUsers(users: List<Game_User>)

    @Insert
    fun insertAllTeamUsers(users: List<Team_User>)

    @Insert
    fun insertAllTeams(users: List<Teams>)
}