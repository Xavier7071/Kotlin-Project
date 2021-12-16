package com.example.project.views

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project.R
import com.example.project.adapter.GamesListAdapter
import com.example.project.controllers.MainController
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TeamActivity : AppCompatActivity() {
    private lateinit var rvMain: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team)

        val teamNameView: TextView = findViewById(R.id.teamNameView)
        teamNameView.text = MainController.instance.getTeamById().name
        val coachNameView: TextView = findViewById(R.id.coachNameView)
        coachNameView.text = MainController.instance.getCoach()

        val addGameBtn: FloatingActionButton = findViewById(R.id.addGameBtn)
        if (MainController.instance.getIsPlayer()) {
            addGameBtn.hide()
        } else {
            addGameBtn.setOnClickListener {
                val intent = Intent(this, CreateGameActivity::class.java)
                startActivity(intent)
            }
        }

        findViewById<Button>(R.id.playersListBtn).setOnClickListener {
            val intent = Intent(this, PlayersListActivity::class.java)
            startActivity(intent)
        }
        loadRecyclerView()
    }

    private fun loadRecyclerView() {
        rvMain = findViewById(R.id.gamesList)
        rvMain.adapter = GamesListAdapter(MainController.instance.getGamesFromTeam())
        rvMain.layoutManager = LinearLayoutManager(this)
    }
}