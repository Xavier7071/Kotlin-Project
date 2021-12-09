package com.example.project.views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.project.R
import com.example.project.controllers.MainController
import java.util.*


class CreateTeamActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_team)


        var validEntry = true //TODO: vérifier si les valeurs sont valide

        val createTeamBtn: Button = findViewById(R.id.createTeamBtn)
        val name: EditText = findViewById(R.id.teamNameInput)
        val mySpinner: Spinner = findViewById(R.id.catergorySpinner)
        val level = mySpinner.selectedItem.toString()

        createTeamBtn.setOnClickListener {
            if (validEntry) {
                val code = generateCode()
                MainController.instance.insertTeam(name.text.toString(), level, code)
                MainController.instance.insertTeamUser(code)
                val intent = Intent(this, AccountActivity::class.java)
                startActivity(intent)
            }
        }

    }

    private fun generateCode(): String {
        val availableChar = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"
        val stringBuilder = StringBuilder()
        val rnd = Random()
        while (stringBuilder.length < 6) {
            val index = (rnd.nextFloat() * availableChar.length).toInt()
            stringBuilder.append(availableChar[index])
        }
        val randomCode = stringBuilder.toString()
        Log.v("test", randomCode)

        if (!isValidCode(randomCode)) {
            generateCode()
        }
        return randomCode
    }

    private fun isValidCode(code: String): Boolean {
        return !MainController.instance.validateTeamExists(code) // car il regarde si l'équipe existe
    }
}