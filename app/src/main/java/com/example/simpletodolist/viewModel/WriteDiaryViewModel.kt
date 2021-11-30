package com.example.simpletodolist.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simpletodolist.database.AppDataBase
import com.example.simpletodolist.database.DiaryItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate

class WriteDiaryViewModel : ViewModel() {

    val curDiary = MutableLiveData<DiaryItem?>()

    fun getDiary(context: Context) {
        var getDiaryData: DiaryItem? = null
        CoroutineScope(Dispatchers.IO).launch {
            if (curDiary.value != null) {
                getDiaryData = AppDataBase.getInstance(context)?.DiaryItemDao()
                    ?.getTargetDiaryItem(curDiary.value?.id!!)
            } else {
                getDiaryData = DiaryItem(
                    treeCategoryId = 0,
                    title = "",
                    content = "",
                    day = LocalDate.now().dayOfMonth.toString(),
                    month = LocalDate.now().month.value,
                    time = LocalDate.now().year.toString() + "." + LocalDate.now().monthValue.toString() + "." + LocalDate.now().dayOfMonth.toString() + " " + LocalDate.now().dayOfWeek.toString()
                        .substring(0, 3)
                )
            }
            withContext(Dispatchers.Main) {
                curDiary.value = getDiaryData!!
            }
        }

    }

    fun writeDiary(context: Context, id: Int, title: String, content: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val diaryItem = DiaryItem(
                treeCategoryId = id,
                title = title,
                content = content,
                day = LocalDate.now().dayOfMonth.toString(),
                month = LocalDate.now().month.value,
                time = LocalDate.now().year.toString() + "." + LocalDate.now().monthValue.toString() + "." + LocalDate.now().dayOfMonth.toString() + " " + LocalDate.now().dayOfWeek.toString()
                    .substring(0, 3)
            )
            AppDataBase.getInstance(context)?.DiaryItemDao()?.insertItem(diaryItem)
        }
    }

}