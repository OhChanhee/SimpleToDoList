package com.example.simpletodolist.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simpletodolist.adapter.TreeListRecyclerviewAdapter
import com.example.simpletodolist.databinding.ActivityTreeListBinding
import com.example.simpletodolist.util.VerticalSpaceItemDecoration
import com.example.simpletodolist.viewModel.TreeListViewModel

class TreeListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTreeListBinding
    private lateinit var recyclerviewAdapter: TreeListRecyclerviewAdapter

    private val viewModel: TreeListViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTreeListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerviewAdapter = TreeListRecyclerviewAdapter(this)
        binding.rvTreeList.adapter = recyclerviewAdapter
        binding.rvTreeList.addItemDecoration(VerticalSpaceItemDecoration(20))
        binding.rvTreeList.layoutManager = LinearLayoutManager(this)

        viewModel.getTreeData(this)

        viewModel.treeData.observe(this, Observer {
            recyclerviewAdapter.data = it.toMutableList()
            recyclerviewAdapter.notifyDataSetChanged()
        })
        viewModel.curYear.observe(this, Observer {
            binding.treeYearTv.text = it.toString()
            viewModel.getTreeData(this)
        })

        binding.plusYearBtn.setOnClickListener{
            viewModel.plusCurYear(binding.treeYearTv.text.toString().toInt() + 1)
        }
        binding.minusYearBtn.setOnClickListener{
            viewModel.minusCurYear(binding.treeYearTv.text.toString().toInt() - 1)
        }
    }

}