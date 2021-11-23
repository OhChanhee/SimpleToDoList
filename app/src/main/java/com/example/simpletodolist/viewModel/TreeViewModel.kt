package com.example.simpletodolist.viewModel

import android.content.Context
import android.util.Log
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
    var curTree = MutableLiveData<Tree?>()

    fun getDiaryData(context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            var getTreeData : Tree? = null
            var getDiaryData: List<DiaryItem>? = null

            if(curTree.value == null)//처음 실행했을때
            {
                val year = LocalDate.now().year
                val month = LocalDate.now().monthValue

                getTreeData = AppDataBase.getInstance(context)?.TreeDao()?.getTargetTree(year,month)?.tree
                if(getTreeData == null)
                {
                    val newTree = Tree(year=year,month =month)
                    AppDataBase.getInstance(context)?.TreeDao()?.insertTree(newTree)
                    getTreeData = AppDataBase.getInstance(context)?.TreeDao()?.getTargetTree(year,month)?.tree

                    getDiaryData = AppDataBase.getInstance(context)?.TreeDao()
                        ?.getTargetTree(getTreeData?.year, getTreeData?.month)?.diaryItemList
                }
            }
            else{
                getDiaryData = AppDataBase.getInstance(context)?.TreeDao()
                    ?.getTargetTree(curTree.value?.year, curTree.value?.month)?.diaryItemList
            }


            withContext(Dispatchers.Main) {
                if(getDiaryData != null) {
                    diaryData.value = getDiaryData!!
                }
                if(getTreeData != null)
                {
                    curTree.value = getTreeData
                }
            }
        }
    }


}