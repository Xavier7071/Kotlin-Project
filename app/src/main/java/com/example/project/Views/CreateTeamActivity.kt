package com.example.project.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class CreateTeamActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.project.R.layout.activity_create_team)


        var validEntry = true

        val createTeamBtn: Button = findViewById(com.example.project.R.id.createTeamBtn)
        createTeamBtn.setOnClickListener {
            if (validEntry) {
                val intent = Intent(this, AccountActivity::class.java)
                startActivity(intent)
            }
        }


    }
}