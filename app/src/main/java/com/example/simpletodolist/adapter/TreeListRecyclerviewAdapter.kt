package com.example.simpletodolist.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.simpletodolist.R
import com.example.simpletodolist.database.TreeWithDiaryData

class TreeListRecyclerviewAdapter() : RecyclerView.Adapter<TreeListRecyclerviewAdapter.ViewHolder>(){
    var data = mutableListOf<TreeWithDiaryData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tree_item,parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position,data)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val month : TextView = itemView.findViewById(R.id.treeItemTitle_tv)
        private val bg : ImageButton = itemView.findViewById(R.id.tree_item_ib)
        fun bind(position: Int,items:List<TreeWithDiaryData>){
            month.text = items[position].tree.month.toString() + "월의 나무"
            bg.clipToOutline = true
        }
    }


}