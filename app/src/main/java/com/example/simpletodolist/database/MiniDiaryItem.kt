package com.example.simpletodolist.database

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Query

data class MiniDiaryItem(
    @ColumnInfo(name = "id")var miniDiaryItemId:Int,
    var day:String,
    var month:Int
)

@Dao
interface MiniDiaryItemDao{
    @Query("SELECT id,day,month FROM tb_diaryItem WHERE treeCategoryId =:id ")
    fun getMiniDiaryItems(id:Int?): List<MiniDiaryItem>
}
