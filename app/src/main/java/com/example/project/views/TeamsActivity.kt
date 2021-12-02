package com.example.project.Views

import android.os.Bundle
import android.text.Editable
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.project.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.StrictMode.VmPolicy

import android.os.StrictMode
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.jar.Manifest


class TeamsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teams2)
        StrictMode.setVmPolicy(
            VmPolicy.Builder()
                .detectLeakedClosableObjects()
                .penaltyLog()
                .build()
        )

        val addTeamBtn: FloatingActionButton = findViewById(R.id.addTeamBtn)
        addTeamBtn.setOnClickListener {
            //dialogAlert()
            val intent = Intent(this, CreateGameActivity::class.java)
            startActivity(intent)
        }
    }

    fun dialogAlert() {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        builder.setTitle("Code de l'équipe")
        val dialogLayout = inflater.inflate(R.layout.alert_dialog_with_edittext, null)
        val codeInput  = dialogLayout.findViewById<EditText>(R.id.codeInput).text
        builder.setView(dialogLayout)
        builder.setPositiveButton("Ajouter") { dialogInterface, i -> addInfo(codeInput)}
        builder.setNegativeButton("Quitter") { dialogInterface, i -> addInfo(codeInput)}
        builder.show()

    }

    fun addInfo(codeInput: Editable) {
        if (codeInput.isNotEmpty()) {
            Toast.makeText(applicationContext, "Code: $codeInput", Toast.LENGTH_SHORT).show()

        } else {
            Toast.makeText(applicationContext, "Code invalide", Toast.LENGTH_SHORT).show()

        }
    }
}