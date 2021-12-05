package com.example.simpletodolist.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simpletodolist.database.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.Year

class TreeViewModel : ViewModel() {

    val diaryData = MutableLiveData<List<MiniDiaryItem>>()
    var curTree = MutableLiveData<Tree?>()

    fun getDiaryData(context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            var getTreeData : Tree? = null
            var getDiaryData: List<MiniDiaryItem>?

            if(curTree.value == null)//처음 실행했을때
            {
                val year = LocalDate.now().year
                val month = LocalDate.now().monthValue

                getTreeData = AppDataBase.getInstance(context)?.TreeDao()?.getTargetTree(year,month)?.tree

                getDiaryData = AppDataBase.getInstance(context)?.MiniDiaryItemDao()?.getMiniDiaryItems(getTreeData?.treeId)

                if(getTreeData == null)
                {
                    val newTree = Tree(year=year,month =month)
                    AppDataBase.getInstance(context)?.TreeDao()?.insertTree(newTree)
                    getTreeData = AppDataBase.getInstance(context)?.TreeDao()?.getTargetTree(year,month)?.tree
                    getDiaryData = AppDataBase.getInstance(context)?.MiniDiaryItemDao()?.getMiniDiaryItems(getTreeData?.treeId)

                }
            }
            else{//처음시작이아닐때
                getDiaryData = AppDataBase.getInstance(context)?.MiniDiaryItemDao()?.getMiniDiaryItems(curTree.value?.treeId)
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