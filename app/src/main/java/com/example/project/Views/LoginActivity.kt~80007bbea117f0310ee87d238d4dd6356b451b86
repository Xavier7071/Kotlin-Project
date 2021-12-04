package com.example.project.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.project.R
import com.example.project.controllers.MainController
import android.content.Intent
import android.net.Uri
import android.widget.Button
import android.widget.TextView
import com.example.project.Views.CreateAccountActivity
import com.example.project.Views.TeamsActivity


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        MainController.instance.loadDatabase(applicationContext)

        val connectBtn: Button = findViewById(R.id.connectBtn)
        val createAccountTextBtn : TextView = findViewById(R.id.createAccountText)

        connectBtn.setOnClickListener {
            val intent = Intent(this, TeamsActivity::class.java)
            startActivity(intent)
        }

        createAccountTextBtn.setOnClickListener {
            val intent = Intent(this, CreateAccountActivity::class.java)
            startActivity(intent)
        }
    }
}



/*
        code pour ouvrir la caméra

    val intent = Intent("android.media.action.IMAGE_CAPTURE")
    startActivity(intent)


    code pour appeler

    val callIntent = Intent(Intent.ACTION_DIAL)
    callIntent.data = Uri.parse("tel:4508088576")
    startActivity(callIntent)
*/