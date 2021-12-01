package com.example.simpletodolist.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.simpletodolist.database.AppDataBase
import com.example.simpletodolist.database.DiaryItem
import com.example.simpletodolist.databinding.ActivityWriteDiaryBinding
import com.example.simpletodolist.viewModel.WriteDiaryViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate

class WriteDiaryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWriteDiaryBinding

    private val viewModel: WriteDiaryViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWriteDiaryBinding.inflate(layoutInflater)
        val id = intent.getIntExtra("treeCategoryId",-1)
        var title : String
        var content : String

        viewModel.getDiary(this,id)

        viewModel.curDiary.observe(this, Observer {
            binding.writeDiaryDateTv.text = it?.time
            binding.diaryTitleEt.setText(it?.title)
            binding.diaryContentEt.setText(it?.content)
        })

        viewModel.writeStatus.observe(this, Observer {
            if(it)
            {
                finish()
            }
        })

        binding.checkBtn.setOnClickListener{
            title = binding.diaryTitleEt.text.toString()
            content = binding.diaryContentEt.text.toString()
            if(title.isBlank()||content.isBlank())
            {
                Toast.makeText(this, "일기가 비어있어요...", Toast.LENGTH_SHORT).show()
            }
            else
            {
                viewModel.writeDiary(this,id,title,content)
            }
        }

        setContentView(binding.root)
    }

}