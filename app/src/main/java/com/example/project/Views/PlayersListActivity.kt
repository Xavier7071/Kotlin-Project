package com.example.project.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project.R
import com.example.project.adapter.PlayersListAdapter
import com.example.project.controllers.MainController

class PlayersListActivity : AppCompatActivity() {
    private lateinit var rvMain: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_players_list)

        loadRecyclerView()
    }

    private fun loadRecyclerView() {
        rvMain = findViewById(R.id.playersListRV)
        rvMain.adapter = PlayersListAdapter(MainController.instance.getPlayersFromTeam())
        rvMain.layoutManager = LinearLayoutManager(this)
    }
}