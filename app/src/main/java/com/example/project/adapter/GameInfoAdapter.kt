package com.example.project.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project.R
import com.example.project.models.Game_User
import com.example.project.models.Users

class GameInfoAdapter(private var gameUserList: ArrayList<Game_User>, private var userList: ArrayList<Users>) :
    RecyclerView.Adapter<GameInfoAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val playerNameView: TextView = view.findViewById(R.id.playerNameView)
        val presenceView: TextView = view.findViewById(R.id.presenceView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.player_info_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentGameUser = gameUserList[position]
        val currentUser = userList[position]
        holder.playerNameView.text = currentUser.name
        if (currentGameUser.isThere) {
            holder.presenceView.text = "Présent"
        } else {
            holder.presenceView.text = "Absent"
        }
    }

    override fun getItemCount(): Int {
        return gameUserList.size
    }
}