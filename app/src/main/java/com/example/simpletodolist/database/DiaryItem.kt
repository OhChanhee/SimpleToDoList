package com.example.simpletodolist.database

import android.os.Parcelable
import androidx.room.*
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "tb_diaryItem")
data class DiaryItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title:String,
    val content:String,
    val time:String,
):Parcelable

