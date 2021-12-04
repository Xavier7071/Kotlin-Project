package com.example.project.controllers

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import androidx.room.Room
import com.example.project.models.*

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

        try {
            database!!.databaseDAO().insertAllUsers(userList)
        } catch (ex: Exception) {
            Log.d("logdemo", "Records already in Database: ${ex.message}")
        }

        try {
            database!!.databaseDAO().insertAllCoaches(coachList)
        } catch (ex: Exception) {
            Log.d("logdemo", "Records already in Database: ${ex.message}")
        }

        try {
            database!!.databaseDAO().insertAllPlayers(playerList)
        } catch (ex: Exception) {
            Log.d("logdemo", "Records already in Database: ${ex.message}")
        }

        try {
            database!!.databaseDAO().insertAllTeams(teamList)
        } catch (ex: Exception) {
            Log.d("logdemo", "Records already in Database: ${ex.message}")
        }

        try {
            database!!.databaseDAO().insertAllTeamUsers(teamUserList)
        } catch (ex: Exception) {
            Log.d("logdemo", "Records already in Database: ${ex.message}")
        }

        try {
            database!!.databaseDAO().insertAllGames(gameList)
        } catch (ex: Exception) {
            Log.d("logdemo", "Records already in Database: ${ex.message}")
        }

        try {
            database!!.databaseDAO().insertAllGameUsers(gameUserList)
        } catch (ex: Exception) {
            Log.d("logdemo", "Records already in Database: ${ex.message}")
        }
    }

    fun getDatabase(): AppDatabase? {
        return database
    }

    fun insertUser(name: String, email: String, password: String, phoneNumber: String) {
        database!!.databaseDAO().insertUser(
            Users(
                (database!!.databaseDAO().findAllUsers().lastIndex + 2),
                name,
                email,
                password,
                phoneNumber
            )
        )
    }

    fun getId(): Int {
        return currentId
    }

    fun setId(id: Int) {
        currentId = id
    }

    fun getTeams(): ArrayList<Teams> {
        teamList.clear()
        teamList.addAll(database!!.databaseDAO().findTeamsById(currentId))
        return teamList
    }
}