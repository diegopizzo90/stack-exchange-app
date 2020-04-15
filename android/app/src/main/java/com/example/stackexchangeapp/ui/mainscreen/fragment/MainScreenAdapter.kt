package com.example.stackexchangeapp.ui.mainscreen.fragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.stackexchangeapp.R
import com.example.stackexchangeapp.business.dataviewmodel.UserView
import kotlinx.android.synthetic.main.item_main_screen_user.view.*

class MainScreenAdapter : RecyclerView.Adapter<MainScreenAdapter.ViewHolder>() {

    private val userList: MutableList<UserView> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context: Context = parent.context
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.item_main_screen_user, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = userList[position]
        holder.reputationTextView.text = user.reputation
        holder.userNameTextView.text = user.userName
    }

    fun addUsers(list: List<UserView>) {
        userList.clear()
        userList.addAll(list)
        notifyDataSetChanged()
    }

    fun clearUsers() {
        userList.clear()
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val reputationTextView: TextView = itemView.tv_main_screen_reputation
        val userNameTextView: TextView = itemView.tv_main_screen_username
    }
}