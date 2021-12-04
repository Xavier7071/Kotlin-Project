package com.example.project.Views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import com.example.project.R

class CreateGameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_game)

        val opponentSpinner: Spinner = findViewById(R.id.opponentSpinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.opponentArray,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            opponentSpinner.adapter = adapter
        }

        val gameLocationSpinner: Spinner = findViewById(R.id.gameLocationSpinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.arenaLocalisationArray,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            gameLocationSpinner.adapter = adapter
        }

        val datePickerBtn : TextView = findViewById(R.id.datePickerBtn)

        datePickerBtn.setOnClickListener {
            //TimePickerFragment().show(supportFragmentManager, "timePicker")
        }

    }
}

/*
    date (date picker dialog)

 */