package com.example.project.views

import android.content.Intent
import android.os.Bundle
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
        val mySpinner: Spinner = findViewById(R.id.categorySpinner)
        val category = mySpinner.selectedItem.toString()
        println(category)
        createTeamBtn.setOnClickListener {
            if (validEntry) {
                val code = generateCode()
                MainController.instance.insertTeam(name.text.toString(), category, code)
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
        if (MainController.instance.validateTeamExists(randomCode)) {
            generateCode()
        }
        return randomCode
    }
}