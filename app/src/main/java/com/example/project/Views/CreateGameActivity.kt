package com.example.project.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.project.R
import com.example.project.controllers.MainController
import com.example.project.models.Teams

class CreateGameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_game)

        val opponentSpinner: Spinner = findViewById(R.id.opponentSpinner)
        opponentSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, MainController.instance.getOtherTeams())


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
        val hourInput: EditText = findViewById(R.id.hourInput)
        val locationSpinner: Spinner = findViewById(R.id.gameLocationSpinner)
        val opponentSpinner: Spinner = findViewById(R.id.opponentSpinner)
        if (hourInput.text.toString().isEmpty()) {
            Toast.makeText(this, "Veuillez entrer une heure pour la partie", Toast.LENGTH_SHORT).show()
        } else {
            val location = locationSpinner.selectedItem.toString()
            val opponent = opponentSpinner.selectedItem.toString()

            MainController.instance.insertTeam(name.text.toString(), category, code)
            MainController.instance.insertTeamUser(code)

            val intent = Intent(this, AccountActivity::class.java)
            startActivity(intent)
        }
    }
}

/*
    date (date picker dialog)

 */