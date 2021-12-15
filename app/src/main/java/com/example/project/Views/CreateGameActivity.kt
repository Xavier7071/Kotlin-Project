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

        val opponentSpinner: Spinner = findViewById(R.id.opponentSpinner)
        opponentSpinner.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            teamNames
        )

        val createGameBtn: Button = findViewById(R.id.createGameBtn)
        createGameBtn.setOnClickListener {
            validateGameInfo()
        }
        val datePicker = findViewById<DatePicker>(R.id.datePicker)
        val today = Calendar.getInstance()
        datePicker.init(
            today.get(Calendar.YEAR), today.get(Calendar.MONTH),
            today.get(Calendar.DAY_OF_MONTH)
        ) { _, year, month, day ->
            savedYear = year
            savedMonth = month
            savedDay = day
        }
    }

    private fun validateGameInfo() {
        val locationSpinner: Spinner = findViewById(R.id.gameLocationSpinner)
        val opponentSpinner: Spinner = findViewById(R.id.opponentSpinner)
        val location = locationSpinner.selectedItem.toString()
        val opponent = opponentSpinner.selectedItem.toString()
        val date = Date()
        date.year = savedYear - 1900
        date.month = savedMonth
        date.date = savedDay
        MainController.instance.insertGame(
            MainController.instance.getTeamByName(opponent),
            location,
            date
        )

        val intent = Intent(this, AccountActivity::class.java)
        startActivity(intent)
    }
}