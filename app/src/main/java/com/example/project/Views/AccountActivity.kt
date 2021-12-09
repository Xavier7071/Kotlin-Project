package com.example.project.views

import android.os.Bundle
import android.text.Editable
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.project.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.os.StrictMode.VmPolicy

import android.os.StrictMode
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project.adapter.TeamsListAdapter
import com.example.project.controllers.MainController


class AccountActivity : AppCompatActivity() {
    private lateinit var rvMain: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.account_recycler_view)

        val addTeamBtn: FloatingActionButton = findViewById(R.id.addTeamBtn)
        addTeamBtn.setOnClickListener {
            dialogAlert()
        }

        loadRecyclerView()
    }

    private fun dialogAlert() {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        builder.setTitle("Code de l'équipe")
        val dialogLayout = inflater.inflate(R.layout.modal_alert, null)
        val codeInput  = dialogLayout.findViewById<EditText>(R.id.codeInput).text
        builder.setView(dialogLayout)
        builder.setPositiveButton("Ajouter") { dialogInterface, i -> addInfo(codeInput)}
        builder.setNegativeButton("Quitter") { dialogInterface, i -> addInfo(codeInput)}
        builder.show()

    }

    private fun addInfo(codeInput: Editable) {
        if (codeInput.isNotEmpty()) {
            Toast.makeText(applicationContext, "Code: $codeInput", Toast.LENGTH_SHORT).show()

        } else {
            Toast.makeText(applicationContext, "Code invalide", Toast.LENGTH_SHORT).show()

        }
    }

    private fun loadRecyclerView() {
        rvMain = findViewById(R.id.teamList)
        rvMain.adapter = TeamsListAdapter(MainController.instance.getTeams())
        rvMain.layoutManager = LinearLayoutManager(this)
    }
}