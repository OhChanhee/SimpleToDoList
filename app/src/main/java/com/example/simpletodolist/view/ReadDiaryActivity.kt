package com.example.simpletodolist.view

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simpletodolist.adapter.ReadDiaryRecyclerviewAdapter
import com.example.simpletodolist.databinding.ActivityReadDiaryBinding
import com.example.simpletodolist.util.RecyclerviewUtil
import com.example.simpletodolist.viewModel.ReadDiaryViewModel

class ReadDiaryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReadDiaryBinding

    private lateinit var recyclerviewAdapter: ReadDiaryRecyclerviewAdapter
    private val viewModel: ReadDiaryViewModel by viewModels()
    private var itemId : Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReadDiaryBinding.inflate(layoutInflater)

        recyclerviewAdapter = ReadDiaryRecyclerviewAdapter()
        binding.diaryImageRecyclerview.adapter = recyclerviewAdapter
        binding.diaryImageRecyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        binding.diaryImageRecyclerview.addItemDecoration(RecyclerviewUtil.RightSpaceItemDecoration(10))

        itemId = intent.getIntExtra("itemId",-1)
        viewModel.getDiary(this, itemId)

        viewModel.curDiary.observe(this, {
            binding.writeDiaryDateTv.text = it?.time
            binding.diaryTitleTv.text = it?.title
            binding.diaryContentTv.text = it?.content

            recyclerviewAdapter.data = it?.imageList!!.toMutableList()
            recyclerviewAdapter.notifyDataSetChanged()
        })

        binding.deleteBtn.setOnClickListener {
            fun onClickDelete(){
                viewModel.deleteDiary(this)
                finish()
            }
            val onClickDeleteListener =
                DialogInterface.OnClickListener { dialog, which ->
                    when(which){
                        DialogInterface.BUTTON_POSITIVE -> onClickDelete()
                    }
                }

           AlertDialog.Builder(this)
                .setTitle("삭제")
                .setMessage("정말 일기를 삭제하실건가요..?")
                .setPositiveButton("확인",onClickDeleteListener)
                .setNegativeButton("취소",null)
                .show()

        }
        binding.fixBtn.setOnClickListener{
            val intent = Intent(this, WriteDiaryActivity::class.java)
            intent.putExtra("itemId",itemId)
            startActivity(intent)
        }
        setContentView(binding.root)
    }

    override fun onRestart() {
        super.onRestart()
        viewModel.getDiary(this, itemId)

    }

}