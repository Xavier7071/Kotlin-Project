package com.example.project.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.project.R
import com.example.project.controllers.MainController

class CreateAccountActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        val takePictureBtn : TextView = findViewById(R.id.takePictureBtn)

        val loginTextBtn : TextView = findViewById(R.id.loginText)
        val createBtn: Button = findViewById(R.id.createBtn)
        findViewById<RadioButton>(R.id.playerBtn).isChecked = true

        createBtn.setOnClickListener {
            createAccount()
        }

        loginTextBtn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        takePictureBtn.setOnClickListener {
            val intent = Intent("android.media.action.IMAGE_CAPTURE")
            startActivity(intent)
        }
    }

    private fun createAccount() {
        val name = findViewById<EditText>(R.id.nameInput)
        val email = findViewById<EditText>(R.id.newEmailInput)
        val password = findViewById<EditText>(R.id.newPasswordInput)
        val confirmPassword = findViewById<EditText>(R.id.confirmPasswordInput)
        val phoneNumber = findViewById<EditText>(R.id.phoneInput)
        val users = MainController.instance.getDatabase()!!.databaseDAO().findAllUsers()

        if (name.text.toString().isEmpty() || email.text.toString().isEmpty() || password.text.toString().isEmpty() || confirmPassword.text.toString().isEmpty() || phoneNumber.text.toString().isEmpty()) {
            Toast.makeText(this, "Veuillez entrer vos informations", Toast.LENGTH_SHORT).show()
        } else if (users.find { it.email == email.text.toString() } != null) {
            Toast.makeText(this, "Un compte existe avec ce email", Toast.LENGTH_SHORT).show()
        } else if (users.find { it.phoneNumber == phoneNumber.text.toString() } != null) {
            Toast.makeText(this, "Un compte existe avec ce numéro de téléphone", Toast.LENGTH_SHORT).show()
        } else if (password.text.toString() != confirmPassword.text.toString()) {
            Toast.makeText(this, "Le mot de passe de confirmation est différent du mot de passe entré", Toast.LENGTH_SHORT).show()
        } else if (!email.text.contains("@")) {
            Toast.makeText(this, "Il faut un @ dans le courriel", Toast.LENGTH_SHORT).show()
        } else {
            MainController.instance.insertUser(name.text.toString(), email.text.toString(), password.text.toString(), phoneNumber.text.toString(), findViewById<RadioButton>(R.id.playerBtn).isChecked)
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}