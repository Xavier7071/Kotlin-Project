package com.example.project.controllers

import android.content.Context
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
        isPlayer: Boolean
    ) {
        val id = database!!.databaseDAO().findAllUsers().lastIndex + 2
        database!!.databaseDAO().insertUser(
            Users(
                id,
                name,
                email,
                password,
                phoneNumber
            )
        )
        if (isPlayer) {
            database!!.databaseDAO()
                .insertPlayer(Players(database!!.databaseDAO().findAllPlayers().lastIndex + 2, id))
        } else {
            database!!.databaseDAO()
                .insertCoach(Coaches(database!!.databaseDAO().findAllCoaches().lastIndex + 2, id))
        }
    }

    fun insertTeam(name: String, category: String, code: String) {
        database!!.databaseDAO().insertTeam(
            Teams(
                database!!.databaseDAO().findAllTeams().lastIndex + 2,
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
                database!!.databaseDAO().findAllTeamUsers().lastIndex + 2,
                currentId,
                team[0].id
            )
        )
    }

    fun insertGame(opponentId: Int, location: String, date: Date) {
        database!!.databaseDAO().insertGame(
            Game(
                database!!.databaseDAO().findAllGames().lastIndex + 2,
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

    fun getTeamById(name: String): Int {
        return database!!.databaseDAO().findTeamById(name)
    }

    private fun getTeamByCode(code: String): ArrayList<Teams> {
        teamList.clear()
        teamList.addAll(database!!.databaseDAO().findTeamByCode(code))
        return teamList
    }
}