package com.example.simpletodolist.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.simpletodolist.R
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
        binding.diaryRecyclerview.layoutManager = GridLayoutManager(this, 8)
        binding.diaryRecyclerview.addItemDecoration(HorizontalItemDecoration(8))
        binding.diaryRecyclerview.adapter = recyclerviewAdapter

        viewModel.curTree = intent.getParcelableExtra("TreeItem")
        binding.yearText.text = viewModel.curTree?.year.toString()
        when (viewModel.curTree?.month) {
            12, 1, 2 -> binding.treeActivityIv.setImageResource(R.drawable.winter_background)
            3, 4, 5 -> binding.treeActivityIv.setImageResource(R.drawable.spring_background)
            6, 7, 8 -> binding.treeActivityIv.setImageResource(R.drawable.summer_background)
            9, 10, 11 -> binding.treeActivityIv.setImageResource(R.drawable.autumn_background)
        }
        when (viewModel.curTree?.month) {
            1 -> binding.monthText.text = "Jan"
            2 -> binding.monthText.text = "Feb"
            3 -> binding.monthText.text = "Mar"
            4 -> binding.monthText.text = "Apr"
            5 -> binding.monthText.text = "May"
            6 -> binding.monthText.text = "Jun"
            7 -> binding.monthText.text = "Jul"
            8 -> binding.monthText.text = "Aug"
            9 -> binding.monthText.text = "Sep"
            10 -> binding.monthText.text = "Oct"
            11 -> binding.monthText.text = "Nov"
            12 -> binding.monthText.text = "Dec"
        }
        viewModel.getDiaryData(this)

        viewModel.diaryData.observe(this, Observer {
            recyclerviewAdapter.data = it.toMutableList()
            recyclerviewAdapter.notifyDataSetChanged()
        })

        binding.treeBtn.setOnClickListener() {
            val intent = Intent(this, TreeListActivity::class.java)
            startActivity(intent)
        }
        binding.writeBtn.setOnClickListener() {
            val intent = Intent(this, WriteDiaryActivity::class.java)
            startActivity(intent)
        }
        binding.settingBtn.setOnClickListener() {
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }

    }
}