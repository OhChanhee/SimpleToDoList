package com.example.simpletodolist.view

import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simpletodolist.R
import com.example.simpletodolist.adapter.ReadDiaryRecyclerviewAdapter
import com.example.simpletodolist.adapter.WriteDiaryRecyclerviewAdapter
import com.example.simpletodolist.database.AppDataBase
import com.example.simpletodolist.database.DiaryItem
import com.example.simpletodolist.databinding.ActivityReadDiaryBinding
import com.example.simpletodolist.util.HorizontalSpaceItemDecoration
import com.example.simpletodolist.viewModel.ReadDiaryViewModel
import com.example.simpletodolist.viewModel.TreeListViewModel
import com.example.simpletodolist.viewModel.WriteDiaryViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import kotlin.math.log

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
        binding.diaryImageRecyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        binding.diaryImageRecyclerview.addItemDecoration(HorizontalSpaceItemDecoration(10))

        itemId = intent.getIntExtra("itemId",-1)
        viewModel.getDiary(this, itemId)

        viewModel.curDiary.observe(this, Observer {
            binding.writeDiaryDateTv.text = it?.time
            binding.diaryTitleTv.text = it?.title
            binding.diaryContentTv.text = it?.content
            //Log.e("ddddd","리사이클러뷰 데이터 : "+it?.imageList!!.toMutableList())
            Log.e("ddddd","리사이클러뷰 데이터 사이즈 : "+it?.imageList!!.toMutableList().size)
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