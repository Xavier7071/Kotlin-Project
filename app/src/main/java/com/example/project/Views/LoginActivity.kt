package com.example.project.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.project.R
import com.example.project.controllers.MainController
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        MainController.instance.loadDatabase(applicationContext)

        val connectBtn: Button = findViewById(R.id.createBtn)
        val createAccountTextBtn : TextView = findViewById(R.id.createAccountText)

        connectBtn.setOnClickListener {
            connection()
        }

        createAccountTextBtn.setOnClickListener {
            val intent = Intent(this, CreateAccountActivity::class.java)
            startActivity(intent)
        }
    }

    private fun connection() {
        val email = findViewById<EditText>(R.id.emailInput)
        val password = findViewById<EditText>(R.id.passwordInput)
        val users = MainController.instance.getDatabase()!!.databaseDAO().findAllUsers()
        if (email.text.toString().isEmpty() || password.text.toString().isEmpty()) {
            Toast.makeText(this, "Veuillez entrer vos informations", Toast.LENGTH_SHORT).show()
        } else if (users.find { it.email == email.text.toString() } == null) {
            Toast.makeText(this, "Email invalide", Toast.LENGTH_SHORT).show()
        } else {
            val user = users.find { it.email == email.text.toString() }
            if (user!!.password != password.text.toString()) {
                Toast.makeText(this, "Mot de passe invalide", Toast.LENGTH_SHORT).show()
            } else {
                MainController.instance.setId(user.id)
                val intent = Intent(this, TeamsActivity::class.java)
                startActivity(intent)
            }
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