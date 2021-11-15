package com.example.simpletodolist.database

import androidx.room.*

@Dao
interface TreeDao {
    @Transaction
    @Query("SELECT * FROM TB_TREE")
    fun getTrees(): List<TreeWithDiaryData>

    @Transaction
    @Query("SELECT * FROM TB_TREE WHERE year = :year AND month = :month")
    fun getTargetTree(year:Int, month:Int) : TreeWithDiaryData?

    @Transaction
    @Query("SELECT * FROM TB_TREE WHERE year = :year")
    fun getYearTrees(year:Int) : List<TreeWithDiaryData>

    @Transaction
    @Insert
    fun insertTree(vararg tree:Tree)
}