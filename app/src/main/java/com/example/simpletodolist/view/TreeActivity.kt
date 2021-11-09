package com.example.simpletodolist.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.simpletodolist.adapter.TreeRecyclerviewAdapter
import com.example.simpletodolist.database.AppDataBase
import com.example.simpletodolist.database.DiaryItem
import com.example.simpletodolist.database.Tree
import com.example.simpletodolist.databinding.ActivityTreeBinding
import com.example.simpletodolist.util.RecyclerviewUtil
import com.example.simpletodolist.util.RecyclerviewUtil.*
import com.example.simpletodolist.viewModel.TreeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate

class TreeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTreeBinding
    private lateinit var recyclerviewAdapter: TreeRecyclerviewAdapter

    private val viewModel: TreeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTreeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerviewAdapter = TreeRecyclerviewAdapter()
        binding.diaryRecyclerview.layoutManager = GridLayoutManager(this,8)
        binding.diaryRecyclerview.addItemDecoration(HorizontalItemDecoration(8))
        binding.diaryRecyclerview.adapter = recyclerviewAdapter

        viewModel.curTree = intent.getParcelableExtra<Tree>("TreeItem")

        viewModel.getDiaryData(this)

        viewModel.diaryData.observe(this, Observer {
            recyclerviewAdapter.data = it.toMutableList()
            recyclerviewAdapter.notifyDataSetChanged()
        })

        binding.treeBtn.setOnClickListener() {
            val intent = Intent(this,TreeListActivity::class.java)
            startActivity(intent)
        }
        binding.writeBtn.setOnClickListener() {

        }
        binding.settingBtn.setOnClickListener() {
            val intent = Intent(this,SettingActivity::class.java)
            startActivity(intent)
        }

    }
}