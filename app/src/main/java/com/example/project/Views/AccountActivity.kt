package com.example.project.views

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project.R
import com.example.project.adapter.TeamsListAdapter
import com.example.project.controllers.MainController
import com.google.android.material.floatingactionbutton.FloatingActionButton


class AccountActivity : AppCompatActivity() {
    private lateinit var rvMain: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.account_recycler_view)
        StrictMode.setVmPolicy(
            VmPolicy.Builder()
                .detectLeakedClosableObjects()
                .penaltyLog()
                .build()
        )

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
        val codeInput = dialogLayout.findViewById<EditText>(R.id.codeInput).text
        builder.setView(dialogLayout)
        builder.setPositiveButton("Ajouter") { _, _ -> validateCode(codeInput.toString()) }
        builder.setNegativeButton("Quitter") { _, _ -> }
        builder.show()

    }

    private fun validateCode(code: String) {
        if (MainController.instance.validateTeamExists(code)) {

        }
    }

    private fun loadRecyclerView() {
        rvMain = findViewById(R.id.teamList)
        rvMain.adapter = TeamsListAdapter(MainController.instance.getTeams())
        rvMain.layoutManager = LinearLayoutManager(this)
    }
}