package com.example.simpletodolist.view

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simpletodolist.adapter.WriteDiaryRecyclerviewAdapter
import com.example.simpletodolist.databinding.ActivityWriteDiaryBinding
import com.example.simpletodolist.util.LoadingDialog
import com.example.simpletodolist.util.RecyclerviewUtil
import com.example.simpletodolist.viewModel.WriteDiaryViewModel

class WriteDiaryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWriteDiaryBinding

    private lateinit var recyclerviewAdapter: WriteDiaryRecyclerviewAdapter
    private val viewModel: WriteDiaryViewModel by viewModels()
    private val getContent =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if(result.data != null)
            {
                val bitmap : Bitmap
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
        binding.diaryImageRecyclerview.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.diaryImageRecyclerview.addItemDecoration(RecyclerviewUtil.RightSpaceItemDecoration(10)
        )

        val dialog = LoadingDialog(this)

        val id = intent.getIntExtra("itemId",-1)
        var title : String
        var content : String
        var imageList : List<Bitmap>

        viewModel.getDiary(this,id)

        viewModel.curDiary.observe(this, {
            binding.writeDiaryDateTv.text = it?.time
            binding.diaryTitleEt.setText(it?.title)
            binding.diaryContentEt.setText(it?.content)
            recyclerviewAdapter.data = stringListToBitMapList(it?.imageList!!)
            recyclerviewAdapter.notifyDataSetChanged()
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

        viewModel.writeStatus.observe(this, {
            finish()
            dialog.dismiss()
        })
        binding.addImageBtn.setOnClickListener{
            if(recyclerviewAdapter.itemCount >= 5)
            {
                Toast.makeText(this, "사진은 5개까지만 넣을수있어요..!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            openGallery()
        }

        setContentView(binding.root)
    }
    private fun openGallery()
    {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type ="image/*"
        getContent.launch(intent)
    }
    private fun stringListToBitMapList(stringList:List<String>) : MutableList<Bitmap> {
        var imageBytes : ByteArray
        var decodedImage : Bitmap
        val resultList = mutableListOf<Bitmap>()
        for (items in stringList){
            imageBytes = Base64.decode(items, Base64.DEFAULT)
            decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
            resultList.add(decodedImage)
        }
        return resultList
    }
}