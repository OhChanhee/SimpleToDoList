package com.example.simpletodolist.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import com.example.simpletodolist.R
import com.example.simpletodolist.database.AppDataBase
import com.example.simpletodolist.database.DiaryItem
import com.example.simpletodolist.databinding.ActivityReadDiaryBinding
import com.example.simpletodolist.viewModel.TreeListViewModel
import com.example.simpletodolist.viewModel.WriteDiaryViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate

class ReadDiaryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReadDiaryBinding

    private val viewModel: WriteDiaryViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReadDiaryBinding.inflate(layoutInflater)

        val item = intent.getParcelableExtra<DiaryItem>("item")
        //binding.editTextTitle.setText(item?.title)
        //binding.editTextContent.setText(item?.content)

//        binding.buttonSave.setOnClickListener {
//            CoroutineScope(Dispatchers.IO).launch {
//                val fixedItem = DiaryItem(
//                    id = item!!.id,
//                    treeCategoryId = 0,
//                    title = binding.diaryTitleTv.text.toString(),
//                    content = binding.diaryContentTv.text.toString(),
//                    day = LocalDate.now().dayOfMonth.toString(),
//                    month = item.month,
//                    time = LocalDate.now().toString(),
//                )
//
//                AppDataBase.getInstance(this@ReadDiaryActivity)!!.DiaryItemDao().updateItem(fixedItem)
//            }
//        }
        binding.deleteBtn.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                AppDataBase.getInstance(this@ReadDiaryActivity)!!.DiaryItemDao().deleteItem(item!!)
            }
        }
        setContentView(binding.root)
    }

}