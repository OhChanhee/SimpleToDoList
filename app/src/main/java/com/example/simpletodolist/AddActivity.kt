package com.example.simpletodolist

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.simpletodolist.database.AppDataBase
import com.example.simpletodolist.database.MemoItem
import com.example.simpletodolist.databinding.ActivityItemaddBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate

class AddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityItemaddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemaddBinding.inflate(layoutInflater)

        binding.buttonAdd.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {

                val item = MemoItem(
                    category = intent.getStringExtra("category").toString(),
                    title = binding.editTextTitle.text.toString(),
                    content = binding.editTextContent.text.toString(),
                    time = LocalDate.now().toString(),
                    isEnd = false
                )
                AppDataBase.getInstance(this@AddActivity)!!.memoItemDao().insertItem(item)
                openMainActivity()
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
            openMainActivity()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun openMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
    }
}