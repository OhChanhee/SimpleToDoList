package com.example.simpletodolist

import androidx.room.*

@Entity(tableName = "tb_memoItem")
data class MemoItem(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val category:String,
    val title:String,
    val content:String,
    val time:String

)

