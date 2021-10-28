package com.example.simpletodolist.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "tb_tree")
data class Tree(
    @PrimaryKey(autoGenerate = true) val treeId: Int = 0,
    var year: Int,
    var month:Int,
): Parcelable
