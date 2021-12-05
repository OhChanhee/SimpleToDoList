package com.example.simpletodolist.database

import androidx.room.*

@Dao
interface DiaryItemDao {
    @Query("SELECT * FROM tb_diaryItem")
    fun getAll():List<DiaryItem>

    @Query("SELECT * FROM tb_diaryItem WHERE treeCategoryId = :id")
    fun getDiaryItems(id: Int):List<DiaryItem>

    @Query("SELECT * FROM tb_diaryItem WHERE id = :id")
    fun getTargetDiaryItem(id: Int) : DiaryItem


    @Query("SELECT COUNT(*) FROM tb_diaryItem")
    fun getItemCount():Int

    @Query("DELETE FROM tb_diaryItem WHERE id = :id")
    fun deleteItemFromID(id:Int)

    @Insert
    fun insertItem(vararg item : DiaryItem)

    @Delete
    fun deleteItem(item : DiaryItem)

    @Update
    fun updateItem(item: DiaryItem)
}