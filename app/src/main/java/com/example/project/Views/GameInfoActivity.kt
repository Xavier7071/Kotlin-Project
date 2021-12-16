package com.example.project.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.project.R
import com.example.project.controllers.MainController

class GameInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_info)

        val game = MainController.instance.getGameById(MainController.instance.getGameId())

        val player = MainController.instance.getPlayerById(MainController.instance.getId())
        player.get(0)

    }
}