package com.example.simpletodolist.database

import androidx.room.*

@Entity(tableName = "tb_memoItem")
data class MemoItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val category:String,
    val title:String,
    val content:String,
    val time:String,
    val isEnd:Boolean
)

