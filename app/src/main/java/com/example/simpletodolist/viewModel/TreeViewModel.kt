package com.example.simpletodolist.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simpletodolist.database.AppDataBase
import com.example.simpletodolist.database.DiaryItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate

class TreeViewModel : ViewModel() {

    var year: Int = 0
    var month: Int = 0

    val diaryData = MutableLiveData<List<DiaryItem>>()

    fun getDiaryData(context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            year = LocalDate.now().year
            month = LocalDate.now().monthValue

            var getDiaryData = AppDataBase.getInstance(context)?.TreeDao()?.getCurTree(year, month)?.diaryItemList

            withContext(Dispatchers.Main) {
                diaryData.value = getDiaryData!!
            }
        }

    }

}