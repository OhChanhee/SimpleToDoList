package com.example.simpletodolist.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.simpletodolist.R
import com.example.simpletodolist.database.DiaryItem
import com.example.simpletodolist.view.ReadDiaryActivity
import com.example.simpletodolist.view.TreeActivity

class TreeRecyclerviewAdapter(private val context: Context) : RecyclerView.Adapter<TreeRecyclerviewAdapter.ViewHolder>() {
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
        private val item_bg :ImageView = itemView.findViewById(R.id.diaryItem_Img)
        fun bind(position: Int,items:List<DiaryItem>){
            day.text = items[position].day
            when(items[position].month)
            {
                1 -> item_bg.setImageResource(R.drawable.item_winter)
                2 -> item_bg.setImageResource(R.drawable.item_winter)
                3 -> item_bg.setImageResource(R.drawable.item_spring)
                4 -> item_bg.setImageResource(R.drawable.item_spring)
                5 -> item_bg.setImageResource(R.drawable.item_spring)
                6 -> item_bg.setImageResource(R.drawable.item_summer)
                7 -> item_bg.setImageResource(R.drawable.item_summer)
                8 -> item_bg.setImageResource(R.drawable.item_summer)
                9 -> item_bg.setImageResource(R.drawable.item_autumn)
                10 -> item_bg.setImageResource(R.drawable.item_autumn)
                11 -> item_bg.setImageResource(R.drawable.item_autumn)
                12 -> item_bg.setImageResource(R.drawable.item_winter)
            }
            item_bg.setOnClickListener {
                val intent = Intent(context, ReadDiaryActivity::class.java)
                intent.putExtra("DiaryItem", items[position])
                context.startActivity(intent)
            }
        }
    }


}