package com.example.simpletodolist.database

import androidx.room.*

@Dao
interface TreeDao {
    @Transaction
    @Query("SELECT * FROM TB_TREE")
    fun getTrees(): List<Tree>
}