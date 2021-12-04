package com.example.project.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.project.models.*

@Dao
interface Database {
    @Insert
    fun insertAllUsers(users: List<Users>)

    @Insert
    fun insertUser(user : Users)

    @Insert
    fun insertAllPlayers(users: List<Players>)

    @Insert
    fun insertPlayer(player : Players)

    @Insert
    fun insertAllCoaches(users: List<Coaches>)

    @Insert
    fun insertCoach(coach : Coaches)

    @Insert
    fun insertAllGames(users: List<Game>)

    @Insert
    fun insertAllGameUsers(users: List<Game_User>)

    @Insert
    fun insertAllTeamUsers(users: List<Team_User>)

    @Insert
    fun insertAllTeams(users: List<Teams>)

    @Query("SELECT * FROM Users")
    fun findAllUsers(): List<Users>

    @Query("SELECT * FROM Teams t JOIN Team_Player tp ON tp.team_id = t.id WHERE tp.user_id IN (:id)")
    fun findTeamsById(id: Int): List<Teams>
}