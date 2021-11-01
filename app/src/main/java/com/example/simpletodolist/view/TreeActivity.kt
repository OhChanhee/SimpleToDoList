package com.example.simpletodolist.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.simpletodolist.adapter.TreeRecyclerviewAdapter
import com.example.simpletodolist.database.DiaryItem
import com.example.simpletodolist.databinding.ActivityTreeBinding
import com.example.simpletodolist.viewModel.TreeViewModel

class TreeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTreeBinding
    private lateinit var recyclerviewAdapter: TreeRecyclerviewAdapter

    private val viewModel : TreeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTreeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerviewAdapter = TreeRecyclerviewAdapter()
        binding.diaryRecyclerview.adapter = recyclerviewAdapter

        viewModel.getDiaryData(this)
        viewModel.diaryData.observe(this, Observer {
            recyclerviewAdapter.data = it.toMutableList()
        })

        binding.treeBtn.setOnClickListener(){

        }
        binding.writeBtn.setOnClickListener(){

        }
        binding.settingBtn.setOnClickListener(){

        }

    }
}