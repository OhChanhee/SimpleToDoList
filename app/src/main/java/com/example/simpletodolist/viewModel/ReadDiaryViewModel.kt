package com.example.simpletodolist.viewModel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simpletodolist.database.AppDataBase
import com.example.simpletodolist.database.DiaryItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ReadDiaryViewModel : ViewModel() {
    val curDiary = MutableLiveData<DiaryItem?>()

    fun getDiary(context: Context,id: Int) {
        var getDiaryData: DiaryItem?
        CoroutineScope(Dispatchers.IO).launch {
            getDiaryData = AppDataBase.getInstance(context)?.DiaryItemDao()?.getTargetDiaryItem(id)

            withContext(Dispatchers.Main) {
                curDiary.value = getDiaryData
            }
        }
    }

    fun deleteDiary(context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            AppDataBase.getInstance(context)?.DiaryItemDao()?.deleteItemFromID(curDiary.value!!.id)
            withContext(Dispatchers.Main) {
                Toast.makeText(context, "일기를 삭제했습니다...", Toast.LENGTH_SHORT).show()
            }
        }
    }
}