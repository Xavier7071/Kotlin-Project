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
    fun insertGame(game: Game)

    @Insert
    fun insertAllGameUsers(users: List<Game_User>)

    @Insert
    fun insertGameUser(users: Game_User)

    @Insert
    fun insertAllTeamUsers(users: List<Team_User>)

    @Insert
    fun insertTeamUser(teamUser: Team_User)

    @Insert
    fun insertAllTeams(users: List<Teams>)

    @Insert
    fun insertTeam(Team: Teams)

    @Query("SELECT * FROM Users")
    fun findAllUsers(): List<Users>

    @Query("SELECT * FROM Teams")
    fun findAllTeams(): List<Teams>

    @Query("SELECT * FROM Teams WHERE id IN (:id)")
    fun findTeamById(id: Int): Teams

    @Query("SELECT id FROM Teams WHERE name = :name")
    fun findTeamByName(name: String): Int

    @Query("SELECT * FROM Teams t JOIN Team_User tp ON tp.team_id = t.id WHERE tp.user_id IN (:id)")
    fun findTeamsById(id: Int): List<Teams>

    @Query("SELECT * FROM Teams t WHERE t.code IN (:code)")
    fun findTeamByCode(code: String): List<Teams>

    @Query("SELECT * FROM Teams WHERE id != :id")
    fun findOtherTeams(id: Int): List<Teams>

    @Query("SELECT * FROM Team_User")
    fun findAllTeamUsers(): List<Team_User>

    @Query("SELECT * FROM Game_User")
    fun findAllGameUsers(): List<Game_User>

    @Query("SELECT * FROM Players")
    fun findAllPlayers(): List<Players>

    @Query("SELECT * FROM Users u JOIN Players p ON u.id = p.user_id JOIN Team_User tu ON tu.user_id = u.id WHERE tu.team_id IN (:id)")
    fun findPlayersByTeam(id: Int): List<Users>

    @Query("SELECT * FROM Players WHERE user_id IN (:id)")
    fun findPlayerById(id: Int): List<Players>

    @Query("SELECT * FROM Game_User WHERE user_id = :playerId AND game_id = :gameId")
    fun playerAlreadyExists(playerId: Int, gameId: Int): Game_User

    @Query("SELECT * FROM Coaches")
    fun findAllCoaches(): List<Coaches>

    @Query("SELECT u.name FROM Users u JOIN Team_User tu ON u.id = tu.user_id JOIN Coaches c ON c.user_id = u.id WHERE tu.team_id IN (:id)")
    fun findCoachByTeamId(id: Int): String

    @Query("SELECT * FROM Game")
    fun findAllGames(): List<Game>

    @Query("SELECT * FROM Game WHERE firstTeam IN (:id) OR secondTeam IN (:id)")
    fun findGamesByTeam(id: Int): List<Game>

    @Query("SELECT * FROM Game WHERE id IN (:id)")
    fun findGameById(id: Int): Game

    @Query("SELECT * FROM Users u JOIN Game_User g ON g.user_id = u.id WHERE team_id = :teamId AND game_id = :gameId")
    fun findUsersForGame(teamId: Int, gameId: Int): List<Users>

    @Query("SELECT * FROM game_user WHERE game_id = :gameId AND team_id = :teamId")
    fun findGameUsers(teamId: Int, gameId: Int): List<Game_User>
}