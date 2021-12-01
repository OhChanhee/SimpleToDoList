package com.example.simpletodolist.util

import androidx.recyclerview.widget.DiffUtil
import com.example.simpletodolist.database.DiaryItem

object DiffCallback : DiffUtil.ItemCallback<DiaryItem>() {
    override fun areItemsTheSame(oldItem: DiaryItem, newItem: DiaryItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DiaryItem, newItem: DiaryItem): Boolean {
        return oldItem == newItem
    }
}