package com.example.simpletodolist.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.simpletodolist.R
import com.example.simpletodolist.database.AppDataBase
import com.example.simpletodolist.database.DiaryItem
import com.example.simpletodolist.databinding.ActivityReadDiaryBinding
import com.example.simpletodolist.viewModel.ReadDiaryViewModel
import com.example.simpletodolist.viewModel.TreeListViewModel
import com.example.simpletodolist.viewModel.WriteDiaryViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate

class ReadDiaryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReadDiaryBinding

    private val viewModel: ReadDiaryViewModel by viewModels()
    private lateinit var item : DiaryItem
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReadDiaryBinding.inflate(layoutInflater)

        item = intent.getParcelableExtra<DiaryItem>("DiaryItem")!!
        viewModel.getDiary(this, item!!.id)

        viewModel.curDiary.observe(this, Observer {
            binding.writeDiaryDateTv.text = it?.time
            binding.diaryTitleTv.setText(it?.title)
            binding.diaryContentTv.setText(it?.content)
        })

        binding.deleteBtn.setOnClickListener {
            viewModel.deleteDiary(this)
            finish()
        }
        binding.fixBtn.setOnClickListener{
            val intent = Intent(this, WriteDiaryActivity::class.java)
            intent.putExtra("treeCategoryId",item.id)
            startActivity(intent)
        }
        setContentView(binding.root)
    }

    override fun onRestart() {
        super.onRestart()
        viewModel.getDiary(this, item!!.id)

    }

}