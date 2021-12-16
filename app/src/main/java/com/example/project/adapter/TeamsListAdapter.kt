package com.example.project.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.project.R
import com.example.project.controllers.MainController
import com.example.project.models.Teams
import com.example.project.views.TeamActivity

class TeamsListAdapter(private var list: ArrayList<Teams>, private var isPlayer: Boolean) :
    RecyclerView.Adapter<TeamsListAdapter.ViewHolder>() {
    private lateinit var context: Context

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.teamName)
        val category: TextView = view.findViewById(R.id.teamLevel)
        val code: TextView? = view.findViewById(R.id.teamCode)
        val button: Button = view.findViewById(R.id.teamButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return if (!isPlayer) {
            ViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.team_info_coach_layout, parent, false)
            )
        } else {
            ViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.team_info_layout, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = list[position]
        holder.name.text = current.name
        holder.category.text = current.category
        if (!isPlayer) {
            holder.code!!.text = current.code
        }
        holder.button.setOnClickListener {
            launchTeamInfo(position + 1)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    private fun launchTeamInfo(id: Int) {
        MainController.instance.setTeamId(id)
        val intent = Intent(context, TeamActivity::class.java)
        startActivity(context, intent, null)
    }
}