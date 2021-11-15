package com.example.simpletodolist.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.simpletodolist.R
import com.example.simpletodolist.database.AppDataBase
import com.example.simpletodolist.database.DiaryItem
import com.example.simpletodolist.databinding.ActivityWriteDiaryBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate

class
WriteDiaryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWriteDiaryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWriteDiaryBinding.inflate(layoutInflater)

        binding.buttonAdd.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {

                val item = DiaryItem(
                    treeCategoryId = 0,
                    title = binding.editTextTitle.text.toString(),
                    content = binding.editTextContent.text.toString(),
                    day = LocalDate.now().dayOfMonth.toString(),
                    time = LocalDate.now().toString()
                )
                AppDataBase.getInstance(this@WriteDiaryActivity)!!.DiaryItemDao().insertItem(item)
                finishActivity()
            }
        }

        setContentView(binding.root)
    }

    private fun finishActivity() {
        finish()
    }
}