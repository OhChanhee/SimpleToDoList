package com.example.simpletodolist.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.simpletodolist.database.DiaryItem
import com.example.simpletodolist.databinding.ActivityTreeBinding
import com.example.simpletodolist.viewModel.TreeViewModel

class TreeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTreeBinding
    private val model:TreeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTreeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.treeBtn.setOnClickListener(){

        }
        binding.writeBtn.setOnClickListener(){

        }
        binding.settingBtn.setOnClickListener(){

        }
        model.getDiaryData().observe(this, Observer<List<DiaryItem>>{
        //리사이클러뷰 UI 수정
        })
    }
}