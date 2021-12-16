package com.example.project.views

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.project.R
import com.example.project.controllers.MainController

class CreateAccountActivity : AppCompatActivity() {
    private val CAMERA_REQUEST_CODE = 0
    private var image: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        val takePictureBtn: TextView = findViewById(R.id.takePictureBtn)

        val loginTextBtn: TextView = findViewById(R.id.loginText)
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
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, CAMERA_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            CAMERA_REQUEST_CODE -> {
                if (resultCode == Activity.RESULT_OK && data != null) {
                    image = (data.extras!!.get("data") as Bitmap)
                }
            }
        }
    }

    private fun createAccount() {
        val name = findViewById<EditText>(R.id.nameInput)
        val email = findViewById<EditText>(R.id.newEmailInput)
        val password = findViewById<EditText>(R.id.newPasswordInput)
        val confirmPassword = findViewById<EditText>(R.id.confirmPasswordInput)
        val phoneNumber = findViewById<EditText>(R.id.phoneInput)
        val users = MainController.instance.getDatabase()!!.databaseDAO().findAllUsers()

        if (name.text.toString().isEmpty() || email.text.toString()
                .isEmpty() || password.text.toString().isEmpty() || confirmPassword.text.toString()
                .isEmpty() || phoneNumber.text.toString().isEmpty()
        ) {
            Toast.makeText(this, "Veuillez entrer vos informations", Toast.LENGTH_SHORT).show()
        } else if (users.find { it.email == email.text.toString() } != null) {
            Toast.makeText(this, "Un compte existe avec ce email", Toast.LENGTH_SHORT).show()
        } else if (users.find { it.phoneNumber == phoneNumber.text.toString() } != null) {
            Toast.makeText(this, "Un compte existe avec ce numéro de téléphone", Toast.LENGTH_SHORT)
                .show()
        } else if (password.text.toString() != confirmPassword.text.toString()) {
            Toast.makeText(
                this,
                "Le mot de passe de confirmation est différent du mot de passe entré",
                Toast.LENGTH_SHORT
            ).show()
        } else if (!email.text.contains("@")) {
            Toast.makeText(this, "Il faut un @ dans le courriel", Toast.LENGTH_SHORT).show()
        } else if (phoneNumber.text.toString().length != 10) {
            Toast.makeText(this, "Il faut entrer un numéro de téléphone valide", Toast.LENGTH_SHORT)
                .show()
        } else if (image == null) {
            Toast.makeText(this, "Il faut prendre une photo", Toast.LENGTH_SHORT).show()
        } else {
            MainController.instance.insertUser(
                name.text.toString(),
                email.text.toString(),
                password.text.toString(),
                phoneNumber.text.toString(),
                findViewById<RadioButton>(R.id.playerBtn).isChecked,
                image!!
            )
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}