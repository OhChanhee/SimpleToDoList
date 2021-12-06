package com.example.simpletodolist.util

import androidx.recyclerview.widget.DiffUtil
import com.example.simpletodolist.database.MiniDiaryItem

object DiaryItemDiffCallback : DiffUtil.ItemCallback<MiniDiaryItem>() {
    override fun areItemsTheSame(oldItem: MiniDiaryItem, newItem: MiniDiaryItem): Boolean {
        return oldItem.miniDiaryItemId == newItem.miniDiaryItemId
    }

    override fun areContentsTheSame(oldItem: MiniDiaryItem, newItem: MiniDiaryItem): Boolean {
        return oldItem == newItem
    }
}