package com.example.simpletodolist.database

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Relation
import kotlinx.parcelize.Parcelize

@Parcelize
data class TreeWithDiaryData(
    @Embedded var tree: Tree,
    @Relation(
        parentColumn = "treeId",
        entityColumn = "treeCategoryId"
    )
    var diaryItemList : List<DiaryItem>

):Parcelable
