package com.example.project.views

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Spinner
import android.widget.TextView
import com.example.project.R
import com.example.project.controllers.MainController

class GameInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_info)

        val game = MainController.instance.getGameById(MainController.instance.getGameId())
        MainController.instance.getId()
        val presenceView: TextView = findViewById(R.id.presenceView)
        if (presenceView.text == "absent") {
            presenceView.setTextColor(Color.RED);
        }
    }
}