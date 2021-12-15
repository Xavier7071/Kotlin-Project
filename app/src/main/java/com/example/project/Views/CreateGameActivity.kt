package com.example.project.views

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.project.R
import com.example.project.controllers.MainController
import com.example.project.models.Teams
import java.util.*
import kotlin.collections.ArrayList

class CreateGameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_game)

        val teamsArray : ArrayList<Teams> = MainController.instance.getOtherTeams()
        val teamNames : ArrayList<String> = ArrayList()
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

        // val datePickerBtn : TextView = findViewById(R.id.datePickerBtn)

        //datePickerBtn.setOnClickListener {
        //  TimePickerFragment().show(supportFragmentManager, "timePicker")
        //}
    }

    private fun validateGameInfo() {
        val locationSpinner: Spinner = findViewById(R.id.gameLocationSpinner)
        val opponentSpinner: Spinner = findViewById(R.id.opponentSpinner)
        val location = locationSpinner.selectedItem.toString()
        val opponent = opponentSpinner.selectedItem.toString()

        MainController.instance.insertGame(MainController.instance.getTeamByName(opponent), location, date = Date())

        val intent = Intent(this, AccountActivity::class.java)
        startActivity(intent)
    }
}

/*
    date (date picker dialog)

 */