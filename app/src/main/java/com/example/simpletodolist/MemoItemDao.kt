package com.example.simpletodolist

import androidx.room.*
import com.example.simpletodolist.MemoItem

@Dao
interface MemoItemDao {
    @Query("SELECT * FROM tb_memoItem")
    fun getAll():List<MemoItem>
    @Insert
    fun insertItem(vararg item : MemoItem)
    @Delete
    fun deleteItem(item : MemoItem)
}