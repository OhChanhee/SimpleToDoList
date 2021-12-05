package com.example.simpletodolist.view

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simpletodolist.adapter.TreeListRecyclerviewAdapter
import com.example.simpletodolist.adapter.WriteDiaryRecyclerviewAdapter
import com.example.simpletodolist.database.AppDataBase
import com.example.simpletodolist.database.DiaryItem
import com.example.simpletodolist.databinding.ActivityWriteDiaryBinding
import com.example.simpletodolist.util.HorizontalSpaceItemDecoration
import com.example.simpletodolist.util.LoadingDialog
import com.example.simpletodolist.viewModel.WriteDiaryViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate

class WriteDiaryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWriteDiaryBinding

    private lateinit var recyclerviewAdapter: WriteDiaryRecyclerviewAdapter
    private val viewModel: WriteDiaryViewModel by viewModels()
    private val getContent =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if(result.data != null)
            {
                var bitmap : Bitmap
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    val source = ImageDecoder.createSource(this.contentResolver, result.data?.data!!)
                    bitmap = ImageDecoder.decodeBitmap(source)
                }
                else{
                    bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver,result.data?.data!!)
                }
                recyclerviewAdapter.data.add(bitmap)
                recyclerviewAdapter.notifyDataSetChanged()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWriteDiaryBinding.inflate(layoutInflater)

        recyclerviewAdapter = WriteDiaryRecyclerviewAdapter()
        binding.diaryImageRecyclerview.adapter = recyclerviewAdapter
        binding.diaryImageRecyclerview.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        binding.diaryImageRecyclerview.addItemDecoration(HorizontalSpaceItemDecoration(10))

        val dialog = LoadingDialog(this)

        val id = intent.getIntExtra("treeCategoryId",-1)
        var title : String
        var content : String
        var imageList : List<Bitmap>

        viewModel.getDiary(this,id)

        viewModel.curDiary.observe(this, Observer {
            binding.writeDiaryDateTv.text = it?.time
            binding.diaryTitleEt.setText(it?.title)
            binding.diaryContentEt.setText(it?.content)
        })

        binding.checkBtn.setOnClickListener{
            title = binding.diaryTitleEt.text.toString()
            content = binding.diaryContentEt.text.toString()
            imageList = recyclerviewAdapter.data.toList()
            if(title.isBlank()||content.isBlank())
            {
                Toast.makeText(this, "일기가 비어있어요...", Toast.LENGTH_SHORT).show()
            }
            else
            {
                viewModel.writeDiary(this,id,title,content,imageList)
                dialog.show()
            }
        }

        viewModel.writeStatus.observe(this, Observer {
            finish()
            dialog.dismiss()
        })
        binding.addImageBtn.setOnClickListener{
            openGallery()
        }


        setContentView(binding.root)
    }
    private fun openGallery()
    {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type ="image/*"
        getContent.launch(intent)
    }
}