package com.example.simpletodolist.view

import android.content.Intent
import android.os.Bundle
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

        viewModel.getDiary(this)

        viewModel.curDiary.observe(this, Observer {
            binding.writeDiaryDateTv.text = it?.time
            binding.diaryTitleEt.setText(it?.title)
            binding.diaryContentEt.setText(it?.content)
        })


        val id = intent.getIntExtra("treeCategoryId",-1)
        val title = binding.diaryTitleEt.text.toString()
        val content = binding.diaryContentEt.text.toString()

        binding.checkBtn.setOnClickListener{
            viewModel.writeDiary(this,id,title,content)
            val intent = Intent(this, TreeActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }

        setContentView(binding.root)
    }

}