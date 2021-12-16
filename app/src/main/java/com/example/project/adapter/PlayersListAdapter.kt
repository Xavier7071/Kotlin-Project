package com.example.project.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.project.R
import com.example.project.models.Users

class PlayersListAdapter(private var list: ArrayList<Users>) :
    RecyclerView.Adapter<PlayersListAdapter.ViewHolder>() {
    private lateinit var context: Context
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val playerImg: ImageView = view.findViewById(R.id.playerImg)
        val playerName: TextView = view.findViewById(R.id.playerName)
        val callBtn: ImageView = view.findViewById(com.example.project.R.id.callBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.player_info_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = list[position]
        holder.playerImg.setImageBitmap(current.image)
        holder.playerName.text = current.name
        holder.callBtn.setOnClickListener {
            callPlayer(current.phoneNumber)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    private fun callPlayer(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$phoneNumber")
        startActivity(context, intent, null)
    }
}