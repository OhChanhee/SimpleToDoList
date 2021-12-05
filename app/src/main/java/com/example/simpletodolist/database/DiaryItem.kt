package com.example.simpletodolist.database

import android.graphics.Bitmap
import android.os.Parcelable
import androidx.room.*
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "tb_diaryItem")
data class DiaryItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var treeCategoryId:Int,
    var title:String,
    var content:String,
    var day:String,
    var month:Int,
    var time:String,
    var imageList:List<String>?
):Parcelable

