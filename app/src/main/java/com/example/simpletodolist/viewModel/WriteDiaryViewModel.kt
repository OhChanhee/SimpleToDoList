package com.example.simpletodolist.viewModel

import android.content.Context
import android.graphics.Bitmap
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simpletodolist.database.AppDataBase
import com.example.simpletodolist.database.DiaryItem
import com.example.simpletodolist.util.SingleLiveEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.time.LocalDate
import kotlin.math.log

class WriteDiaryViewModel : ViewModel() {

    val curDiary = MutableLiveData<DiaryItem?>()
    val writeStatus = SingleLiveEvent<Void>()

    fun getDiary(context: Context,id: Int) {
        var getDiaryData: DiaryItem?
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
                        .substring(0, 3),
                    imageList = emptyList<String>()
                )
            }
            withContext(Dispatchers.Main) {
                curDiary.value = getDiaryData!!
            }
        }

    }

    fun writeDiary(context: Context, id: Int, title: String, content: String,imageList:List<Bitmap>) {
        CoroutineScope(Dispatchers.IO).launch {
            val treeId: Int
            val itemId : Int
            val imageStringMutableList = mutableListOf<String>()
            val imageStringList : List<String>?
            val baos = ByteArrayOutputStream()
            var bytes : ByteArray


            if(imageList.isNotEmpty())
            {
                for(bitmapItem in imageList)
                {
                    bitmapItem.compress(Bitmap.CompressFormat.PNG,10,baos)
                    bytes = baos.toByteArray()
                    imageStringMutableList.add(Base64.encodeToString(bytes,Base64.DEFAULT))
                }
                imageStringList = imageStringMutableList.toList()
            }
            else
            {
                imageStringList = emptyList<String>()
            }
            Log.e("dddd","imageStringList Size: " + imageStringList.size.toString())
            if(id == -1)//메인에서 글을썼을때
            {
                treeId = AppDataBase.getInstance(context)?.TreeDao()?.getTargetTree(LocalDate.now().year,LocalDate.now().monthValue)?.tree?.treeId!!
                AppDataBase.getInstance(context)?.DiaryItemDao()?.insertItem( DiaryItem(
                    treeCategoryId = treeId,
                    title = title,
                    content = content,
                    day = LocalDate.now().dayOfMonth.toString(),
                    month = LocalDate.now().month.value,
                    time = LocalDate.now().year.toString() + "." + LocalDate.now().monthValue.toString() + "." + LocalDate.now().dayOfMonth.toString() + " " + LocalDate.now().dayOfWeek.toString()
                        .substring(0, 3),
                    imageList = imageStringList
                ))


                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "일기를 작성했습니다!", Toast.LENGTH_SHORT).show()
                    writeStatus.call()
                }
            }
            else //아이템을 수정할때
            {
                itemId = id
                val fixItem = AppDataBase.getInstance(context)?.DiaryItemDao()?.getTargetDiaryItem(itemId)
                AppDataBase.getInstance(context)?.DiaryItemDao()?.updateItem(DiaryItem(
                    id = itemId,
                    treeCategoryId = fixItem!!.treeCategoryId,
                    title = title,
                    content = content,
                    day = fixItem.day,
                    month = fixItem.month,
                    time = fixItem.time,
                    imageList = imageStringList
                ))
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "일기를 수정했습니다!", Toast.LENGTH_SHORT).show()
                    writeStatus.call()
                }
            }

        }
    }

}