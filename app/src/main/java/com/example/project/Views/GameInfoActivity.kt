package com.example.project.views

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project.R
import com.example.project.adapter.GameInfoAdapter
import com.example.project.controllers.MainController

class GameInfoActivity : AppCompatActivity() {
    private lateinit var rvMain: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_info)

        val game = MainController.instance.getGameById(MainController.instance.getGameId())
        val locationView: TextView = findViewById(R.id.locationView)
        locationView.text = game.location
        val dateView: TextView = findViewById(R.id.dateView)
        dateView.text = game.date.day.toString() + "/" + game.date.month.toString() + "/" + game.date.year.toString()

        val presentButton: RadioButton = findViewById(R.id.presentButton)
        presentButton.setOnClickListener {
            if (presentButton.isChecked) {
                if (MainController.instance.getIsPlayer()) {
                    MainController.instance.insertGameUser(true)
                }
                loadRecyclerView()
            }
        }
        val absentButton: RadioButton = findViewById(R.id.absentButton)
        absentButton.setOnClickListener {
            if (absentButton.isChecked) {
                if (MainController.instance.getIsPlayer()) {
                    MainController.instance.insertGameUser(false)
                }
                loadRecyclerView()
            }
        }
        loadRecyclerView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_home -> {
                val intent = Intent(this, AccountActivity::class.java)
                startActivity(intent)
            }
            R.id.action_disconnect -> {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }
        return false
    }

    private fun loadRecyclerView() {
        rvMain = findViewById(R.id.presenceRV)
        rvMain.adapter = GameInfoAdapter(MainController.instance.getGameUsers(), MainController.instance.getUsersForGame())
        rvMain.layoutManager = LinearLayoutManager(this)
    }
}