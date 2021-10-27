package com.example.simpletodolist.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "tb_tree")
data class Tree(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val year: Int,
    val month:Int,
    val diaryItemList:List<DiaryItem>
): Parcelable
