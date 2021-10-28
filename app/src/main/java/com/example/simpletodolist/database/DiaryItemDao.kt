package com.example.simpletodolist.database

import androidx.room.*

@Dao
interface DiaryItemDao {
    @Query("SELECT * FROM tb_diaryItem")
    fun getAll():List<DiaryItem>

    @Query("SELECT * FROM tb_diaryItem WHERE treeCategoryId = :category")
    fun getDiaryItems(category: Int):List<DiaryItem>

    @Query("SELECT COUNT(*) FROM tb_diaryItem")
    fun getItemCount():Int

    @Insert
    fun insertItem(vararg item : DiaryItem)

    @Delete
    fun deleteItem(item : DiaryItem)

    @Update
    fun updateItem(item: DiaryItem)
}