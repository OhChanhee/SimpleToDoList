package com.example.simpletodolist.database

import androidx.room.*

@Dao
interface MemoItemDao {
    @Query("SELECT * FROM tb_memoItem")
    fun getAll():List<MemoItem>
    @Query("SELECT * FROM tb_memoItem WHERE category ='BusyWorks' ")
    fun getBusyWorksMemo():List<MemoItem>
    @Query("SELECT * FROM tb_memoItem WHERE category ='FreeWorks' ")
    fun getFreeWorksMemo():List<MemoItem>
    @Query("SELECT * FROM tb_memoItem WHERE category ='WishWorks' ")
    fun getWishWorksMemo():List<MemoItem>
    @Query("SELECT COUNT(*) FROM tb_memoItem")
    fun getItemCount():Int
    @Insert
    fun insertItem(vararg item : MemoItem)
    @Delete
    fun deleteItem(item : MemoItem)
    @Update
    fun updateItem(item: MemoItem)
}