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
    fun insertUser(user: Users)

    @Insert
    fun insertAllPlayers(users: List<Players>)

    @Insert
    fun insertPlayer(player: Players)

    @Insert
    fun insertAllCoaches(users: List<Coaches>)

    @Insert
    fun insertCoach(coach: Coaches)

    @Insert
    fun insertAllGames(users: List<Game>)

    @Insert
    fun insertAllGameUsers(users: List<Game_User>)

    @Insert
    fun insertAllTeamUsers(users: List<Team_User>)

    @Insert
    fun insertTeam(Team: Teams)

    @Insert
    fun insertAllTeams(users: List<Teams>)

    @Insert
    fun insertTeamUser(teamUser: Team_User)

    @Insert
    fun insertGame(game: Game)

    @Query("SELECT * FROM Users")
    fun findAllUsers(): List<Users>

    @Query("SELECT * FROM Teams")
    fun findAllTeams(): List<Teams>

    @Query("SELECT * FROM Teams WHERE id != :id")
    fun findOtherTeams(id: Int): List<Teams>

    @Query("SELECT * FROM Teams t JOIN Team_User tp ON tp.team_id = t.id WHERE tp.user_id IN (:id)")
    fun findTeamsById(id: Int): List<Teams>

    @Query("SELECT * FROM Teams t WHERE t.code IN (:code)")
    fun findTeamByCode(code: String): List<Teams>

    @Query("SELECT * FROM Players WHERE user_id IN (:id)")
    fun findPlayerById(id: Int): List<Players>

    @Query("SELECT * FROM Team_User")
    fun findAllTeamUsers(): List<Team_User>

    @Query("SELECT id FROM Teams WHERE name = :name")
    fun findTeamByName(name: String): Int

    @Query("SELECT * FROM Game WHERE firstTeam IN (:id)")
    fun findGamesByTeam(id: Int): List<Game>

    @Query("SELECT u.name FROM Users u JOIN Team_User tu ON u.id = tu.user_id JOIN Coaches c ON c.user_id = u.id WHERE tu.team_id IN (:id)")
    fun findCoachByTeamId(id: Int): String

    @Query("SELECT * FROM Users u JOIN Players p ON u.id = p.user_id JOIN Team_User tu ON tu.user_id = u.id WHERE tu.team_id IN (:id)")
    fun findPlayersByTeam(id: Int): List<Users>

    @Query("SELECT * FROM Teams WHERE id IN (:id)")
    fun findTeamById(id: Int): Teams

    @Query("SELECT * FROM Players")
    fun findAllPlayers(): List<Players>

    @Query("SELECT * FROM Coaches")
    fun findAllCoaches(): List<Coaches>

    @Query("SELECT * FROM Game")
    fun findAllGames(): List<Game>

    @Query("SELECT * FROM Game WHERE id IN (:id)")
    fun findGameById(id: Int): Game
}