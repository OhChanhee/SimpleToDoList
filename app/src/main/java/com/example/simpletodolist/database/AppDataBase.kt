package com.example.simpletodolist.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MemoItem::class],version = 1)
abstract class AppDataBase : RoomDatabase(){
    abstract fun memoItemDao(): MemoItemDao

    companion object {
        private var instance : AppDataBase? = null

        fun getInstance(context: Context): AppDataBase?{
            if(instance == null)
            {
                synchronized(AppDataBase::class)
                {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDataBase::class.java, "memoitem.db"
                    ).build()
                }
            }
            return instance
        }
    }
}