package com.example.simpletodolist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.simpletodolist.R
import com.example.simpletodolist.database.DiaryItem

class TreeRecyclerviewAdapter() : RecyclerView.Adapter<TreeRecyclerviewAdapter.ViewHolder>() {
    var data = mutableListOf<DiaryItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.diary_item,parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position,data)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        private val day :TextView = itemView.findViewById(R.id.diaryItem_text)

        fun bind(position: Int,items:List<DiaryItem>){
            day.text = items[position].day
        }
    }


}