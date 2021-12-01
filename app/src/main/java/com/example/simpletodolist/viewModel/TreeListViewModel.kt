package com.example.simpletodolist.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simpletodolist.database.AppDataBase
import com.example.simpletodolist.database.TreeWithDiaryData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate

class TreeListViewModel : ViewModel() {
    val treeData = MutableLiveData<List<TreeWithDiaryData>>()
    val curYear : MutableLiveData<Int> = MutableLiveData<Int>().apply { value = LocalDate.now().year }

    fun getTreeData(context: Context)
    {
        CoroutineScope(Dispatchers.IO).launch {
            val getTreeData = AppDataBase.getInstance(context)?.TreeDao()?.getYearTrees(curYear.value!!)

            withContext(Dispatchers.Main) {
                treeData.value = getTreeData!!
            }
        }
    }
    fun plusCurYear(value : Int)
    {
        if(value < LocalDate.now().year) //현재년도보다 작을시
        curYear.value = value
    }
    fun minusCurYear(value : Int)
    {
        if(value > 2020)
        curYear.value = value
    }
}