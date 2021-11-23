package com.example.simpletodolist.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.simpletodolist.R
import com.example.simpletodolist.database.TreeWithDiaryData
import com.example.simpletodolist.view.TreeActivity
import com.example.simpletodolist.view.TreeListActivity

class TreeListRecyclerviewAdapter(private val context: Context) : RecyclerView.Adapter<TreeListRecyclerviewAdapter.ViewHolder>(){
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

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val month : TextView = itemView.findViewById(R.id.treeItemTitle_tv)
        private val btn : ImageButton = itemView.findViewById(R.id.tree_item_ib)
        fun bind(position: Int,items:List<TreeWithDiaryData>){
            month.text = items[position].tree.month.toString() + "월의 나무"
            btn.clipToOutline = true
            when(items[position].tree.month)
            {
                1 -> btn.setImageResource(R.drawable.tree_item_january)
                2 -> btn.setImageResource(R.drawable.tree_item_february)
                3 -> btn.setImageResource(R.drawable.tree_item_march)
                4 -> btn.setImageResource(R.drawable.tree_item_april)
                5 -> btn.setImageResource(R.drawable.tree_item_may)
                6 -> btn.setImageResource(R.drawable.tree_item_june)
                7 -> btn.setImageResource(R.drawable.tree_item_july)
                8 -> btn.setImageResource(R.drawable.tree_item_august)
                9 -> btn.setImageResource(R.drawable.tree_item_september)
                10 -> btn.setImageResource(R.drawable.tree_item_october)
                11 -> btn.setImageResource(R.drawable.tree_item_nobember)
                12 -> btn.setImageResource(R.drawable.tree_item_december)
            }
            btn.setOnClickListener {
                val intent = Intent(context,TreeActivity::class.java)
                intent.putExtra("TreeItem", items[position].tree)
                context.startActivity(intent)
            }
        }
    }


}