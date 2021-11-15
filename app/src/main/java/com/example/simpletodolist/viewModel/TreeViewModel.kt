package com.example.simpletodolist.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simpletodolist.database.AppDataBase
import com.example.simpletodolist.database.DiaryItem
import com.example.simpletodolist.database.Tree
import com.example.simpletodolist.database.TreeWithDiaryData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.Year

class TreeViewModel : ViewModel() {

    val diaryData = MutableLiveData<List<DiaryItem>>()
    var curTree : Tree? = Tree(0,0,0)

    fun getDiaryData(context: Context) {
        CoroutineScope(Dispatchers.IO).launch {

            if(curTree == null)//처음 실행했을때
            {
                var year = LocalDate.now().year
                var month = LocalDate.now().monthValue

                curTree = AppDataBase.getInstance(context)?.TreeDao()?.getTargetTree(year,month)?.tree
                if(curTree == null)
                {
                    val newTree = Tree(year=year,month =month)
                    AppDataBase.getInstance(context)?.TreeDao()?.insertTree(newTree)
                    curTree = AppDataBase.getInstance(context)?.TreeDao()?.getTargetTree(year,month)?.tree
                }
            }

            var getDiaryData = AppDataBase.getInstance(context)?.TreeDao()
                ?.getTargetTree(curTree!!.year, curTree!!.month)?.diaryItemList

            withContext(Dispatchers.Main) {
                diaryData.value = getDiaryData!!
            }
        }
    }


}