package com.example.simpletodolist.view

import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.simpletodolist.R
import com.example.simpletodolist.adapter.TreeListRecyclerviewAdapter
import com.example.simpletodolist.adapter.TreeRecyclerviewAdapter
import com.example.simpletodolist.databinding.ActivityTreeBinding
import com.example.simpletodolist.databinding.ActivityTreeListBinding
import com.example.simpletodolist.viewModel.TreeListViewModel
import com.example.simpletodolist.viewModel.TreeViewModel

class TreeListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTreeListBinding
    private lateinit var recyclerviewAdapter: TreeListRecyclerviewAdapter

    private val viewModel: TreeListViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTreeListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerviewAdapter = TreeListRecyclerviewAdapter()
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

    inner class VerticalSpaceItemDecoration(private val verticalSpaceHeight: Int) :
        RecyclerView.ItemDecoration() {

        override fun getItemOffsets(
            outRect: Rect, view: View, parent: RecyclerView,
            state: RecyclerView.State
        ) {
            outRect.top = verticalSpaceHeight
        }
    }
}