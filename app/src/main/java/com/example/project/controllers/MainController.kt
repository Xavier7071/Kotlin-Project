package com.example.project.controllers

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import androidx.room.Room
import com.example.project.models.*
import java.util.*
import kotlin.collections.ArrayList
import android.R.color
import android.graphics.Canvas
import android.graphics.Paint
import androidx.core.graphics.blue
import java.time.LocalDate


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
        val bitmap = Bitmap.createBitmap(25, 25, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val paint = Paint()
        paint.color = paint.color.blue
        canvas.drawRect(0f, 0f, 25.toFloat(), 25.toFloat(), paint)
        val date = Date()
        date.year = 2021
        date.month = 12
        date.date = 21

        userList.add(Users(1, "Joe", "Joe@hotmail.fr", "joe", bitmap, "4508463611"))
        userList.add(Users(2, "Xavier", "Xavier@hotmail.fr", "xavier", bitmap, "4507800315"))
        userList.add(Users(3, "Ludo", "Ludo@hotmail.fr", "ludo", bitmap, "4507263627"))
        userList.add(Users(4, "Admin", "Admin@hotmail.fr", "admin", bitmap, "4502736263"))
        userList.add(Users(5, "Roger", "Roger@hotmail.fr", "roger", bitmap, "4502619473"))
        coachList.add(Coaches(1, 4))
        coachList.add(Coaches(2, 5))
        playerList.add(Players(1, 1))
        playerList.add(Players(2, 2))
        playerList.add(Players(3, 3))
        teamList.add(Teams(1, "Mariniers", "AAA", "FG3H"))
        teamList.add(Teams(2, "Canadiens", "AAA", "K2K3"))
        teamUserList.add(Team_User(1, 1, 1))
        teamUserList.add(Team_User(2, 2, 1))
        teamUserList.add(Team_User(3, 3, 2))
        teamUserList.add(Team_User(4, 4, 1))
        teamUserList.add(Team_User(5, 5, 2))
        gameList.add(Game(1, 1, 2, "Colisée Cardin", date))
        gameList.add(Game(2, 2, 1, "Arena St-Joseph", date))
        gameUserList.add(Game_User(1, 1, 1, 1, false))
        gameUserList.add(Game_User(2, 2, 1, 1, true))
        gameUserList.add(Game_User(3, 3, 1, 2, true))
        gameUserList.add(Game_User(4, 1, 2, 1, true))
        gameUserList.add(Game_User(5, 2, 2, 1, false))
        gameUserList.add(Game_User(6, 3, 2, 2, true))

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
            database!!.databaseDAO().insertAllTeams(teamList)
        } catch (ex: Exception) {
            Log.d("logdemo", "Records already in Database: ${ex.message}")
        }
        try {
            database!!.databaseDAO().insertAllPlayers(playerList)
        } catch (ex: Exception) {
            Log.d("logdemo", "Records already in Database: ${ex.message}")
        }
        try {
            database!!.databaseDAO().insertAllCoaches(coachList)
        } catch (ex: Exception) {
            Log.d("logdemo", "Records already in Database: ${ex.message}")
        }
        try {
            database!!.databaseDAO().insertAllGameUsers(gameUserList)
        } catch (ex: Exception) {
            Log.d("logdemo", "Records already in Database: ${ex.message}")
        }
        try {
            database!!.databaseDAO().insertAllGames(gameList)
        } catch (ex: Exception) {
            Log.d("logdemo", "Records already in Database: ${ex.message}")
        }
        try {
            database!!.databaseDAO().insertAllTeamUsers(teamUserList)
        } catch (ex: Exception) {
            Log.d("logdemo", "Records already in Database: ${ex.message}")
        }
        println(database!!.databaseDAO().findAllUsers())
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
                database!!.databaseDAO().findAllUsers().lastIndex + 2,
                name,
                email,
                password,
                image,
                phoneNumber
            )
        )
        if (isPlayer) {
            database!!.databaseDAO()
                .insertPlayer(Players(database!!.databaseDAO().findAllPlayers().lastIndex + 2, database!!.databaseDAO().findAllUsers().lastIndex + 1))
        } else {
            database!!.databaseDAO()
                .insertCoach(Coaches(database!!.databaseDAO().findAllCoaches().lastIndex + 2, database!!.databaseDAO().findAllUsers().lastIndex + 1))
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
        println(teamList)
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

    fun insertGameUser(isThere: Boolean) {
        if (database!!.databaseDAO().playerAlreadyExists(currentId, gameId) == null) {
            database!!.databaseDAO().insertGameUser(
                Game_User(
                    database!!.databaseDAO().findAllGameUsers().lastIndex + 2,
                    currentId,
                    gameId,
                    teamId,
                    isThere
                )
            )
        }
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

    fun getGameId(): Int {
        return gameId
    }

    fun getGameById(id: Int): Game {
        return database!!.databaseDAO().findGameById(id)
    }

    fun getCoach(): String {
        return database!!.databaseDAO().findCoachByTeamId(teamId)
    }

    fun getUsersForGame(): ArrayList<Users> {
        userList.clear()
        userList.addAll(database!!.databaseDAO().findUsersForGame(teamId, gameId))
        return userList
    }

    fun getGameUsers(): ArrayList<Game_User> {
        gameUserList.clear()
        gameUserList.addAll(database!!.databaseDAO().findGameUsers(teamId, gameId))
        return gameUserList
    }

    private fun getTeamByCode(code: String): ArrayList<Teams> {
        teamList.clear()
        teamList.addAll(database!!.databaseDAO().findTeamByCode(code))
        return teamList
    }
}