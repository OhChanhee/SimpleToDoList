package com.example.simpletodolist.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.simpletodolist.R
import com.example.simpletodolist.database.DiaryItem
import com.example.simpletodolist.database.MiniDiaryItem
import com.example.simpletodolist.util.DiaryItemDiffCallback
import com.example.simpletodolist.view.ReadDiaryActivity

class TreeRecyclerviewAdapter(private val context: Context) : ListAdapter<MiniDiaryItem, TreeRecyclerviewAdapter.ViewHolder>(DiaryItemDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.diary_item,parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        private val day :TextView = itemView.findViewById(R.id.diaryItem_text)
        private val item_bg :ImageView = itemView.findViewById(R.id.diaryItem_Img)
        fun bind(item:MiniDiaryItem){
            day.text = item.day
            when(item.month)
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
                intent.putExtra("itemId",item.miniDiaryItemId)
                context.startActivity(intent)
            }
        }
    }


}