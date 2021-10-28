package com.example.simpletodolist.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simpletodolist.database.AppDataBase
import com.example.simpletodolist.database.DiaryItem

class TreeViewModel : ViewModel() {

    var year : Int = 0
    var month : Int = 0

    private val diaryData : MutableLiveData<List<DiaryItem>> by lazy {
        MutableLiveData<List<DiaryItem>>().also {
           loadDiaryData()
        }
    }

    fun getDiaryData() : LiveData<List<DiaryItem>>  {
        return diaryData
    }

    private fun loadDiaryData()
    {

    }
}