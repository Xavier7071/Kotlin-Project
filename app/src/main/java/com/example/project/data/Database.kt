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

    @Insert
    fun insertTeamUser(teamUser: Team_User)

    @Query("SELECT * FROM Users")
    fun findAllUsers(): List<Users>

    @Query("SELECT * FROM Teams t JOIN Team_User tp ON tp.team_id = t.id WHERE tp.user_id IN (:id)")
    fun findTeamsById(id: Int): List<Teams>


    @Query("SELECT * FROM Teams t WHERE t.code IN (:code)")
    fun findTeamByCode(code: String): List<Teams>

    @Query("SELECT * FROM Players WHERE id IN (:id)")
    fun findPlayerById(id: Int): List<Players>

    @Query("SELECT * FROM Team_User")
    fun findAllTeamUsers(): List<Team_User>


    @Query("SELECT * FROM Players")
    fun findAllPlayers(): List<Players>

    @Query("SELECT * FROM Coaches")
    fun findAllCoaches(): List<Coaches>
}