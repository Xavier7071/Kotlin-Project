package com.example.project.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import com.example.project.R
import com.example.project.controllers.MainController

class TeamInfosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_infos)
        val currentTeamName = MainController.instance.getTeamById().name

    }
}