package com.example.project.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project.R
import com.example.project.controllers.MainController
import com.example.project.models.Teams

class TeamsListAdapter(private var list: ArrayList<Teams>) :
    RecyclerView.Adapter<TeamsListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.teamName)
        val category: TextView = view.findViewById(R.id.teamLevel)
        val button: Button = view.findViewById(R.id.teamButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.team_info_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = list[position]
        holder.name.text = current.name
        holder.category.text = current.category
        holder.button.setOnClickListener {
            launchTeamInfo(current.id)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    private fun launchTeamInfo(id: Int) {
        MainController.instance.setTeamId(id)
        //launch icitte l'activité de l'équipe avec l'id de l'équipe pour le query
    }
}