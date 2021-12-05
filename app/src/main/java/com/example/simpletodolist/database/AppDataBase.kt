package com.example.simpletodolist.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [DiaryItem::class,Tree::class],version = 1)
@TypeConverters(TypeConverter::class)
abstract class AppDataBase : RoomDatabase(){

    abstract fun DiaryItemDao(): DiaryItemDao
    abstract fun TreeDao(): TreeDao
    abstract fun MiniDiaryItemDao() : MiniDiaryItemDao
    companion object {
        private var instance : AppDataBase? = null

        fun getInstance(context: Context): AppDataBase?{
            if(instance == null)
            {
                synchronized(AppDataBase::class)
                {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDataBase::class.java, "diaryItem.db"
                    ).build()
                }
            }
            return instance
        }
    }
}