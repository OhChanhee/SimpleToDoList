package com.example.simpletodolist.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.simpletodolist.R
import com.example.simpletodolist.database.AppDataBase
import com.example.simpletodolist.database.MemoItem
import com.example.simpletodolist.databinding.ActivityItemBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate

class ReadDiaryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityItemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemBinding.inflate(layoutInflater)

        val item = intent.getParcelableExtra<MemoItem>("item")
        binding.editTextTitle.setText(item?.title)
        binding.editTextContent.setText(item?.content)

        binding.buttonSave.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val fixeditem = MemoItem(
                    id = item!!.id,
                    category = item!!.category,
                    title = binding.editTextTitle.text.toString(),
                    content = binding.editTextContent.text.toString(),
                    time = LocalDate.now().toString(),
                    isEnd = item!!.isEnd
                )

                AppDataBase.getInstance(this@ReadDiaryActivity)!!.memoItemDao().updateItem(fixeditem)
                finishActivity()
            }
        }
        binding.buttonDelete.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                AppDataBase.getInstance(this@ReadDiaryActivity)!!.memoItemDao().deleteItem(item!!)
                finishActivity()
            }
        }
        setContentView(binding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_action_bar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.app_bar_back) {
            finishActivity()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun finishActivity() {
        finish()
    }
}