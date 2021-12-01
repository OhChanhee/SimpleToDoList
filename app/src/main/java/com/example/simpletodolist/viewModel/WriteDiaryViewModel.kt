package com.example.simpletodolist.viewModel

import android.content.Context
import android.util.Log
import android.widget.Toast
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
    val writeStatus = MutableLiveData<Boolean>().apply { value = false }

    fun getDiary(context: Context,id: Int) {
        var getDiaryData: DiaryItem? = null
        CoroutineScope(Dispatchers.IO).launch {
            if(id != -1) {
                getDiaryData = AppDataBase.getInstance(context)?.DiaryItemDao()
                    ?.getTargetDiaryItem(id)
            }
            else {
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
            val itemid: Int
            if(id == -1)//메인에서 글을썼을때
            {
                itemid = AppDataBase.getInstance(context)?.TreeDao()?.getTargetTree(LocalDate.now().year,LocalDate.now().monthValue)?.tree?.treeId!!
                AppDataBase.getInstance(context)?.DiaryItemDao()?.insertItem(DiaryItem(
                    treeCategoryId = itemid,
                    title = title,
                    content = content,
                    day = LocalDate.now().dayOfMonth.toString(),
                    month = LocalDate.now().month.value,
                    time = LocalDate.now().year.toString() + "." + LocalDate.now().monthValue.toString() + "." + LocalDate.now().dayOfMonth.toString() + " " + LocalDate.now().dayOfWeek.toString()
                        .substring(0, 3)
                ))
                Log.e("ddddd","작성끝!")
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "일기를 작성했습니다!", Toast.LENGTH_SHORT).show()
                    writeStatus.value = true
                }
            }
            else //아이템을 수정할때
            {
                itemid = id
                val fixItem = AppDataBase.getInstance(context)?.DiaryItemDao()?.getTargetDiaryItem(itemid)
                AppDataBase.getInstance(context)?.DiaryItemDao()?.updateItem(DiaryItem(
                    id = itemid,
                    treeCategoryId = fixItem!!.treeCategoryId,
                    title = title,
                    content = content,
                    day = fixItem.day,
                    month = fixItem.month,
                    time = fixItem.time
                ))
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "일기를 수정했습니다!", Toast.LENGTH_SHORT).show()
                    writeStatus.value = true
                }
            }

        }
    }

}