package com.example.project.controllers

import android.content.Context
import android.graphics.Bitmap
import androidx.room.Room
import com.example.project.models.*
import java.util.*
import kotlin.collections.ArrayList

class MainController private constructor() {
    private var database: AppDatabase? = null
    private var userList = ArrayList<Users>()
    private var coachList = ArrayList<Coaches>()
    private var playerList = ArrayList<Players>()
    private var teamList = ArrayList<Teams>()
    private var teamUserList = ArrayList<Team_User>()
    private var gameList = ArrayList<Game>()
    private var gameUserList = ArrayList<Game_User>()
    private var currentId = 0
    private var isPlayer = true
    private var teamId = 0
    private var gameId = 0

    private object HOLDER {
        val INSTANCE = MainController()
    }

    companion object {
        val instance: MainController by lazy { HOLDER.INSTANCE }
    }

    fun loadDatabase(applicationContext: Context) {
        database = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    fun getDatabase(): AppDatabase? {
        return database
    }

    fun insertUser(
        name: String,
        email: String,
        password: String,
        phoneNumber: String,
        isPlayer: Boolean,
        image: Bitmap
    ) {
        database!!.databaseDAO().insertUser(
            Users(
                0,
                name,
                email,
                password,
                image,
                phoneNumber
            )
        )
        if (isPlayer) {
            database!!.databaseDAO()
                .insertPlayer(Players(0, database!!.databaseDAO().findAllUsers().lastIndex + 1))
        } else {
            database!!.databaseDAO()
                .insertCoach(Coaches(0, database!!.databaseDAO().findAllUsers().lastIndex + 1))
        }
    }

    fun insertTeam(name: String, category: String, code: String) {
        database!!.databaseDAO().insertTeam(
            Teams(
                0,
                name,
                category,
                code
            )
        )
    }

    fun getId(): Int {
        return currentId
    }

    fun setId(id: Int) {
        currentId = id
    }

    fun getIsPlayer(): Boolean {
        return isPlayer
    }

    fun setUserType(isPlayer: Boolean) {
        this.isPlayer = isPlayer
    }

    fun getTeams(): ArrayList<Teams> {
        teamList.clear()
        teamList.addAll(database!!.databaseDAO().findTeamsById(currentId))
        return teamList
    }

    fun getOtherTeams(): ArrayList<Teams> {
        teamList.clear()
        teamList.addAll(database!!.databaseDAO().findOtherTeams(teamId))
        return teamList
    }

    fun validateTeamExists(code: String): Boolean {
        if (database!!.databaseDAO().findTeamByCode(code).isEmpty()) {
            return false
        }
        return true
    }

    fun insertTeamUser(code: String) {
        val team = getTeamByCode(code)
        database!!.databaseDAO().insertTeamUser(
            Team_User(
                0,
                currentId,
                team[0].id
            )
        )
    }

    fun insertGame(opponentId: Int, location: String, date: Date) {
        database!!.databaseDAO().insertGame(
            Game(
                0,
                teamId,
                opponentId,
                location,
                date
            )
        )
    }

    fun getPlayerById(id: Int): ArrayList<Players> {
        playerList.clear()
        playerList.addAll(database!!.databaseDAO().findPlayerById(id))
        return playerList
    }

    fun setTeamId(id: Int) {
        teamId = id
    }

    fun setGameId(id: Int) {
        gameId = id
    }

    fun getTeamByName(name: String): Int {
        return database!!.databaseDAO().findTeamByName(name)
    }

    fun getTeamById(): Teams {
        return database!!.databaseDAO().findTeamById(teamId)
    }

    fun getGamesFromTeam(): ArrayList<Game> {
        gameList.clear()
        gameList.addAll(database!!.databaseDAO().findGamesByTeam(teamId))
        return gameList
    }

    fun getPlayersFromTeam(): ArrayList<Users> {
        userList.clear()
        userList.addAll(database!!.databaseDAO().findPlayersByTeam(teamId))
        return userList
    }

    fun getCoach(): String {
        return database!!.databaseDAO().findCoachByTeamId(teamId)
    }

    private fun getTeamByCode(code: String): ArrayList<Teams> {
        teamList.clear()
        teamList.addAll(database!!.databaseDAO().findTeamByCode(code))
        return teamList
    }
}