package com.example.project.views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.DatePicker
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.project.R
import com.example.project.controllers.MainController
import com.example.project.models.Teams
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.log

class CreateGameActivity : AppCompatActivity() {
    private var savedYear: Int = 0
    private var savedMonth: Int = 0
    private var savedDay: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_game)

        val teamsArray: ArrayList<Teams> = MainController.instance.getOtherTeams()
        val teamNames: ArrayList<String> = ArrayList()
        teamsArray.forEach {
            teamNames.add(it.name)
        }

        findViewById<Spinner>(R.id.opponentSpinner).adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            teamNames
        )
        findViewById<Button>(R.id.createGameBtn).setOnClickListener {
            validateGameInfo()
        }

        val today = Calendar.getInstance()
        findViewById<DatePicker>(R.id.datePicker).init(
            today.get(Calendar.YEAR), today.get(Calendar.MONTH),
            today.get(Calendar.DAY_OF_MONTH)
        ) { _, year, month, day ->
            savedYear = year
            savedMonth = month
            savedDay = day
        }
    }

    private fun validateGameInfo() {
        val location = findViewById<Spinner>(R.id.gameLocationSpinner).selectedItem.toString()
        val opponent = findViewById<Spinner>(R.id.opponentSpinner).selectedItem.toString()
        val date = Date()
        date.year = savedYear - 1900
        date.month = savedMonth
        date.date = savedDay
        MainController.instance.insertGame(
            MainController.instance.getTeamByName(opponent),
            location,
            date
        )

        val intent = Intent(this, TeamActivity::class.java)
        startActivity(intent)
    }
}