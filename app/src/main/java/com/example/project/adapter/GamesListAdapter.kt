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
import com.example.project.models.Game
import com.example.project.views.GameInfoActivity

class GamesListAdapter(private var list: ArrayList<Game>) :
    RecyclerView.Adapter<GamesListAdapter.ViewHolder>() {
    private lateinit var context: Context
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val gameLocation: TextView = view.findViewById(R.id.gameLocation)
        val gameDate: TextView = view.findViewById(R.id.gameDate)
        val button: Button = view.findViewById(R.id.gameButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.game_info_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = list[position]
        holder.gameLocation.text = "#" + position + " " + current.location
        holder.gameDate.text = current.date.day.toString() + "/" + current.date.month.toString() + "/" + current.date.year.toString()
        holder.button.setOnClickListener {
            launchGameInfo(current.id)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    private fun launchGameInfo(id: Int) {
        MainController.instance.setGameId(id)
        val intent = Intent(context, GameInfoActivity::class.java)
        startActivity(context, intent, null)
    }
}